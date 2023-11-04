package pochemon;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

class BackgroundMusic {
    private Clip clip;
    private String path = "../res/musics/";

    BackgroundMusic(String name) {
        this.loadFile(name);
    }

    private void loadFile(String name) {
        File file;
        path += name+".wav";

        try {
            file = new File(path);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
        } catch(IOException e) {
            System.out.println(name+".wav doesn't exist.");
            System.exit(1);
        } catch(LineUnavailableException e) {
            e.printStackTrace();
        } catch(UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
