package com.luruoyang.banner;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.PrintStream;

/**
 * @author luruoyang
 */
public class MyBanner implements Banner {
  @Override
  public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
    out.println("\"自定义Banner实现, Banner接口\"");
    out.println("  _____    ___     ___    _____            ___     ___    _  _    _  _     ___     ___   \n" +
        " |_   _|  | __|   / __|  |_   _|   ___    | _ )   /   \\  | \\| |  | \\| |   | __|   | _ \\  \n" +
        "   | |    | _|    \\__ \\    | |    |___|   | _ \\   | - |  | .` |  | .` |   | _|    |   /  \n" +
        "  _|_|_   |___|   |___/   _|_|_   _____   |___/   |_|_|  |_|\\_|  |_|\\_|   |___|   |_|_\\  ");
  }
}