# suriVlloRkciR ehT

This is the suriVlloRkciR™, the best way to rickroll someone with a (pretty much harmless) computer virus. Just download and send it to your target. The second they open it, the rickroll will show up on their screen!<br>
However, there is a twist: They can't close the window. It will stay there... forever... (until you restart your pc though)
<br><br>

### Download guide:
1. Go to the [Releases](https://github.com/ItsNukea/suriVlloRkciR/releases) page
2. Press the latest release
3. There are 2 download options:
   - Via an `.exe` file
   - Via a `.bat` file

The `.bat` file has a MUCH smaller size and is easier distributable, but requires an internet connection to launch the first time (it downloads and runs the `.exe` file. The file contents are also easily readable with a text editor<br>
The `.exe` file can be ran regardless of internet connection

### Features:
- Fully invisible for your anti-virus
- (almost) impossible to close or switch windows
- A good ol' "run this file for free [ENTER_CURRENCY_HERE]"

### Current Restrictions:
- Users with a touchpad can still evade the window switch by swiping left/right/up with 4 fingers
- Only supports Windows 11 (Might support Windows 10 too, however, I didn't test it)

### Other info
For the developers under you wanting to know how I did it:
- The program has been written in Java
- I used a JFrame set to fullscreen with the close operation set as `JFrame.DO_NOTHING_ON_CLOSE`: basically freezing the program while playing the rickroll
- I set a `Timer` every 10 ms to request focus and bringing the window to the front
- Low-level keyboard press blocker, using JNA
- To display the video, I used JavaFX Media
