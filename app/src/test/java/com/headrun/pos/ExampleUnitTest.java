package com.headrun.pos;

import com.headrun.pos.ui.pages.main.MainActivity;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {

//        MainActivity mainActivity = new MainActivity();

        Result result = JUnitCore.runClasses(MainActivity.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        assertEquals(4, 2 + 2);
    }
}