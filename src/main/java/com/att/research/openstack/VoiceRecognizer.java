package com.att.research.openstack;

import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.frontend.util.AudioFileDataSource;
import edu.cmu.sphinx.result.Result;
import java.util.List;
import java.util.ArrayList;
import java.net.URL;


public class VoiceRecognizer
{
    public VoiceRecognizer()
    {
    }

    List<String> recognize(URL recording, String configuration) {
        ConfigurationManager manager = new ConfigurationManager(getClass().getResource(configuration));
        Recognizer recognizer = (Recognizer) manager.lookup("recognizer");
        recognizer.allocate();
        AudioFileDataSource source = (AudioFileDataSource) manager.lookup("audioFileDataSource");
        source.setAudioFile(recording, null);
        Result result;
        List<String> tokens = new ArrayList<String>();
        while ((result = recognizer.recognize()) != null) {
            String token = result.getBestFinalResultNoFiller();
            for(String temp: token.split(" ")) {
                tokens.add(temp);
            }
        }
        recognizer.deallocate();
        return tokens;
    }

}
