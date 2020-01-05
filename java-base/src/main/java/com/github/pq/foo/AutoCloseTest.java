package com.github.pq.foo;

import org.junit.Test;

import java.io.*;

/**
 * @author xiaoniu 2019/7/10.
 */
public class AutoCloseTest {
    @Test
    public void test1(){
        BufferedInputStream bin = null;
        BufferedOutputStream bout = null;
        try {
            bin = new BufferedInputStream(new FileInputStream(new File("/app/test/in.txt")));
            bout = new BufferedOutputStream(new FileOutputStream(new File("/app/test/out.txt")));
            int b;
            while ((b = bin.read()) != -1) {
                System.out.println(b);
                bout.write(b);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (bin != null) {
                try {
                    bin.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if (bout != null) {
                        try {
                            bout.close();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
