package com.rickrollvirus;

import com.sun.jna.*;
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.WinUser.*;

import java.awt.event.*;

public final class AltTabHooker {

    private static HHOOK hhk;
    private static LowLevelKeyboardProc proc;

    private static final int WH_KEYBOARD_LL = 13;
    private static final int WM_KEYDOWN = 0x0100;
    private static final int WM_SYSKEYDOWN = 0x0104;

    private static final int VK_TAB   = 0x09;
    private static final int VK_LWIN  = 0x5B;
    private static final int VK_RWIN  = 0x5C;
    private static final int VK_UP   = 0x26;
    private static final int VK_DOWN = 0x28;
    private static final int VK_LEFT  = 0x25;
    private static final int VK_RIGHT = 0x27;
    private static final int VK_D     = 0x44;
    private static final int VK_F4    = 0x73;

    private AltTabHooker() {}

    public static synchronized void init() {
        javafx.application.Platform.runLater(() -> {
            if (hhk != null) {
                return;
            }

            proc = (nCode, wParam, info) -> {
                if (nCode >= 0 && isBlockableEvent(wParam, info)) {
                    return new LRESULT(1); // swallow it
                }
                return User32.INSTANCE.CallNextHookEx(hhk, nCode, wParam, structToLParam(info));
            };

            HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
            hhk = User32.INSTANCE.SetWindowsHookEx(WH_KEYBOARD_LL, proc, hMod, 0);

            if (hhk == null) {
                throw new IllegalStateException(
                        "Failed to install keyboard hook, error=" + Kernel32.INSTANCE.GetLastError());
            }
        });
    }

    public static synchronized void finish() {
        if (hhk != null) {
            User32.INSTANCE.UnhookWindowsHookEx(hhk);
            hhk = null;
            proc = null;
        }
    }

    private static boolean isBlockableEvent(WPARAM wParam, KBDLLHOOKSTRUCT info) {
        int msg = wParam.intValue();
        if (msg != WM_KEYDOWN && msg != WM_SYSKEYDOWN) {
            return false;
        }

        int vkCode = info.vkCode;

        boolean altDown  = (User32.INSTANCE.GetAsyncKeyState(0x12) & 0x8000) != 0; // VK_MENU
        boolean ctrlDown = (User32.INSTANCE.GetAsyncKeyState(0x11) & 0x8000) != 0; // VK_CONTROL
        boolean winDown  = (User32.INSTANCE.GetAsyncKeyState(VK_LWIN) & 0x8000) != 0
                || (User32.INSTANCE.GetAsyncKeyState(VK_RWIN) & 0x8000) != 0;

        boolean isAltTab      = vkCode == VK_TAB && altDown;
        boolean isWinKeyAlone = vkCode == VK_LWIN || vkCode == VK_RWIN;
        boolean isTaskView    = winDown && vkCode == VK_TAB;
        boolean isDesktopSwitch = ctrlDown && winDown && (
                vkCode == VK_LEFT  ||
                vkCode == VK_RIGHT ||
                vkCode == VK_D     ||
                vkCode == VK_F4    ||
                vkCode == VK_UP    ||
                vkCode == VK_DOWN
        );

        return isAltTab || isWinKeyAlone || isTaskView || isDesktopSwitch;
    }

    // CallNextHookEx needs the original LPARAM back, not the struct — rebuild it from the struct's pointer.
    private static LPARAM structToLParam(KBDLLHOOKSTRUCT info) {
        Pointer p = info.getPointer();
        return new LPARAM(Pointer.nativeValue(p));
    }
}