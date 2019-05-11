package com.kylodw.bitmap.testhttp.data;

import java.util.Map;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/30
 * 字典树  处理字符串
 */
public class TrieDemo {
    public static void main(String[] args) {
        char c = '我';
        System.out.println(c);
        Trie trie = new Trie();
        trie.add("kylodw");
        System.out.println(trie.contains("ky"));
        System.out.println(trie.contains("kylodw"));
    }
}

