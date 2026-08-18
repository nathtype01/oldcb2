/*
 * classe écrite à la main (pas décompilée) pour jouer assets/sounds/boot.mp3
 * une fois que le jeu a fini de démarrer
 *
 * réutilise javazoom.jl.player.Player déjà présent dans le jar (le module
 * "radio" du client s'en sert déjà pour du streaming mp3) plutôt que
 * d'ajouter une dépendance en plus
 *
 * pour le volume max : sous-classe IlIIlIIIIlllIlIllIlIIlIIl (l'AudioDevice
 * perso déjà utilisé par le module radio) juste pour remplacer l'ouverture de
 * la ligne audio -- l'original lit le volume depuis le réglage du module
 * radio qui existe pas ici donc on force direct le gain au max une fois la
 * ligne ouverte au lieu de dépendre de ce réglage
 *
 * lancé sur son propre thread pour pas bloquer le jeu pendant la lecture
 * (mp3 stéréo court donc pas besoin de gérer un arrêt propre à la fermeture
 * du jeu la jvm coupe le thread daemon toute seule)
 */
import java.io.InputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javazoom.jl.player.Player;

public final class CBBootSound {
    private static boolean played = false;

    private CBBootSound() {
    }

    public static void playOnce() {
        if (played) {
            return;
        }
        played = true;
        Thread thread = new Thread(CBBootSound::play, "cb-boot-sound");
        thread.setDaemon(true);
        thread.start();
    }

    private static void play() {
        try (InputStream inputStream = CBBootSound.class.getResourceAsStream("/assets/sounds/boot.mp3")) {
            if (inputStream == null) {
                return;
            }
            Player player = new Player(inputStream, new MaxVolumeAudioDevice());
            player.play();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static final class MaxVolumeAudioDevice extends IlIIlIIIIlllIlIllIlIIlIIl {
        @Override
        public void IllllIllIIIlllIIllllllIII() {
            try {
                Line line = AudioSystem.getLine(this.llIIlIlIllIlIIIllIllllIlI());
                if (line instanceof SourceDataLine) {
                    this.lllIllIllIlIIIlllIIllllII = (SourceDataLine) line;
                    this.lllIllIllIlIIIlllIIllllII.open(this.llIIlIlIllIlIIIllIllllIlI);
                    this.lllIllIllIlIIIlllIIllllII.start();
                    if (this.lllIllIllIlIIIlllIIllllII.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                        this.lllIllIllIlIIIlllIIllllII(100.0f);
                    }
                }
            }
            catch (LinkageError | RuntimeException | LineUnavailableException throwable) {
                // ligne audio indisponible -- on laisse juste tomber la lecture
            }
        }
    }
}
