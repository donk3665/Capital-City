package GUI.Screens.ScreenElements;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

public class SoundController {
    private MediaPlayer players[];
    private AudioClip clips[];
    private double musicVolume;
    private double effectVolume;
    private MediaPlayer lastPlayer;

    private MediaPlayer lastGamePlayer;
    public SoundController(){
        musicVolume = 0.5;
        effectVolume = 0.5;
        players = new MediaPlayer[10];
        Media media = new Media(Objects.requireNonNull(getClass().getResource("/Music/ST-1930s.mp3")).toExternalForm());
        players[0] = new MediaPlayer(media);

        media = new Media(Objects.requireNonNull(getClass().getResource("/Music/ST-1940s.mp3")).toExternalForm());
        players[1] = new MediaPlayer(media);

        media = new Media(Objects.requireNonNull(getClass().getResource("/Music/ST-1950s.mp3")).toExternalForm());
        players[2] = new MediaPlayer(media);

        media = new Media(Objects.requireNonNull(getClass().getResource("/Music/ST-1960s.mp3")).toExternalForm());
        players[3] = new MediaPlayer(media);

        media = new Media(Objects.requireNonNull(getClass().getResource("/Music/ST-1970s.mp3")).toExternalForm());
        players[4] = new MediaPlayer(media);

        media = new Media(Objects.requireNonNull(getClass().getResource("/Music/ST-1980s.mp3")).toExternalForm());
        players[5] = new MediaPlayer(media);

        media = new Media(Objects.requireNonNull(getClass().getResource("/Music/ST-1990s.mp3")).toExternalForm());
        players[6] = new MediaPlayer(media);

        media = new Media(Objects.requireNonNull(getClass().getResource("/Music/ST-Main_Menu.mp3")).toExternalForm());
        players[7] = new MediaPlayer(media);

        media = new Media(Objects.requireNonNull(getClass().getResource("/Music/ST-Winner.mp3")).toExternalForm());
        players[8] = new MediaPlayer(media);
        media = new Media(Objects.requireNonNull(getClass().getResource("/Music/ST-Auction.mp3")).toExternalForm());
        players[9] = new MediaPlayer(media);

        for (int i = 0; i<7; i++){
            int finalI = i;
            players[i].setOnEndOfMedia(()->{
                playMusic((finalI +1) % 7);
                lastGamePlayer = players[(finalI +1) % 7];
            });
        }
        for (int i = 7; i<10; i++){
            int finalI = i;
            players[i].setOnEndOfMedia(()->{
                lastPlayer = null;
                playMusic(finalI);
            });
        }
        clips = new AudioClip[2];
        clips[0] = new AudioClip(Objects.requireNonNull(getClass().getResource("/Sound/click.wav")).toExternalForm());
        clips[1] = new AudioClip(Objects.requireNonNull(getClass().getResource("/Sound/auction.wav")).toExternalForm());

    }
    public void playClip(int index) {
        clips[index].setVolume(effectVolume);
        clips[index].play();
    }

    public void playGameMusic(){
        if (lastPlayer == lastGamePlayer){
            return;
        }
        if (lastGamePlayer == null){
            playMusic(0);
            lastGamePlayer = players[0];
        }
        else {
            lastPlayer.stop();
            lastGamePlayer.play();
        }
        lastPlayer = lastGamePlayer;
    }
    public void playWinMusic(){
        playMusic(8);
    }
    public void playAuctionMusic(){
        playMusic(9);
    }
    public void playMenuMusic(){
        playMusic(7);
    }
    private void playMusic(int index){
        if (lastPlayer == players[index]){
            return;
        }
        if (lastPlayer != null){
            lastPlayer.stop();
        }
        lastPlayer = players[index];
        players[index].stop();
        players[index].play();
        players[index].setVolume(musicVolume);
    }
    public void changeMusicVolume(double volume){
        this.musicVolume = volume;
        lastPlayer.setVolume(volume);
    }
    public void changeEffectVolume(double volume){
        this.effectVolume= volume;
    }
    public double getMusicVolume() {
        return musicVolume;
    }

    public double getEffectVolume() {
        return effectVolume;
    }
}
