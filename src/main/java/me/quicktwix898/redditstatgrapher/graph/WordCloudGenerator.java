package me.quicktwix898.redditstatgrapher.graph;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.LayeredWordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.PixelBoundryBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class WordCloudGenerator extends JFrame implements GraphGenerator {
    private LayeredWordCloud wordCloud;
    private static final Dimension SIZE = new Dimension(720, 720);

    //change these to make the word cloud look less crammed
    private static final SqrtFontScalar FONT_SIZE = new SqrtFontScalar(10, 40);
    private static final int NUM_WORDS = 400;
    private static final int MIN_CHAR_LENGTH = 4;
    private static final int PADDING = 3;

    public WordCloudGenerator(List<String> input) {
        List<List<WordFrequency>> freq = createDataset(input);
        final Dimension dimension = new Dimension(SIZE);
        wordCloud = new LayeredWordCloud(2, dimension, CollisionMode.PIXEL_PERFECT);
        wordCloud.setPadding(0, PADDING);
        wordCloud.setPadding(1, PADDING);

        try {
            wordCloud.setBackground(0, new PixelBoundryBackground(
                    ClassLoader.getSystemResource("redditlogoback.png").getFile()));
            wordCloud.setBackground(1, new PixelBoundryBackground(
                    ClassLoader.getSystemResource("redditlogowhite.png").getFile()));
        } catch (IOException e) {
            System.out.println("Resource not found, " + e);
        }

        wordCloud.setColorPalette(0, new ColorPalette(new Color(0xff4301)));
        wordCloud.setColorPalette(1, new ColorPalette(new Color(0xffffff)));

        wordCloud.setFontScalar(0, FONT_SIZE);
        wordCloud.setFontScalar(1, FONT_SIZE);

        wordCloud.build(0, freq.get(0));
        wordCloud.build(1, freq.get(1));
    }

    private static List<List<WordFrequency>> createDataset(List<String> input) {
        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        frequencyAnalyzer.setWordFrequenciesToReturn(NUM_WORDS);
        frequencyAnalyzer.setMinWordLength(MIN_CHAR_LENGTH);
        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(input);
        List<List<WordFrequency>> result = new ArrayList<>();
        result.add(new ArrayList<WordFrequency>());
        result.add(new ArrayList<WordFrequency>());
        for(int i = 0; i < wordFrequencies.size(); i++){
            if(i % 2 == 0){
                result.get(0).add(wordFrequencies.get(i));
            }else{
                result.get(1).add(wordFrequencies.get(i));
            }
        }
        return result;
    }

    @Override
    public void openWindow() {
        JPanel panel = new JPanel();
        panel.setSize(SIZE);
        panel.setBackground(Color.BLACK);
        ImageIcon icon = new ImageIcon(wordCloud.getBufferedImage());
        JLabel label = new JLabel();
        label.setIcon(icon);
        panel.add(label);
        this.getContentPane().add(panel);
        this.setVisible(true);
    }

    @Override
    public void save(String filename) {
        wordCloud.writeToFile(filename);
    }
}
