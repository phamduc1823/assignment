package assignment.server.controller;

import assignment.server.application.Command;
import java.util.Map;
import java.util.function.Function;

public interface Controller {
  Map<String, Function<Command, Object>> getMapping();
}
