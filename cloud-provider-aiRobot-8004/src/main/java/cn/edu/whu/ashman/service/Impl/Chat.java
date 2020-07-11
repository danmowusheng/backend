package cn.edu.whu.ashman.service.Impl;

import bitoflife.chatterbean.AliceBot;
import bitoflife.chatterbean.aiml.Aiml;
import bitoflife.chatterbean.util.Searcher;
import cn.edu.whu.ashman.service.IChat;
import cn.edu.whu.ashman.service.Impl.Mother;
import cn.edu.whu.ashman.utils.CosineSimilar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.InitBinder;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Author Liu WeiFan
 * @create 2020年7月11日 20：56
 */
@Service
public class Chat implements IChat {
    public static final String END = "bye";

    @Autowired
    Mother mother = null;
    @Autowired
    CosineSimilar similar;


    /**
     * public static String input()
     * {
     * BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
     * String input = "";
     * try
     * {
     * input = in.readLine();
     * } catch (IOException e) {
     * // TODO Auto-generated catch block
     * e.printStackTrace();
     * }
     * return input;
     * }
     */

    public String getRespond(String message) throws Exception {
        //Mother mother = new Mother();
        mother.setUp();
        AliceBot bot = mother.newInstance();
        //System.err.println("Alice>" + bot.respond("welcome"));
        //String input = Chat.input();
        //if (Chat.END.equalsIgnoreCase(input))
        //System.err.println("Alice>" + bot.respond(input));

        return bot.respond(message);
    }

    public List<String> getSimilarQuestion(String message) {
        return similar.sims(message);
    }

}
