package net.geekscore.services;

import net.geekscore.api.Greeting;
import net.geekscore.core.Loggable;

public interface GreetService extends Loggable {
    Greeting greet(String name);
}
