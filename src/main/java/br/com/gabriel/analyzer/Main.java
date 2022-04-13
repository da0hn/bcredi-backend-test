package br.com.gabriel.analyzer;

import br.com.gabriel.analyzer.events.executors.RemoveWarrantyEventExecutor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public final class Main {

  private static final Logger LOGGER = Logger.getLogger("main");

  public static void main(final String[] args) throws Exception {
    LOGGER.info("Starting tests...");

    final String[] inputFiles = {
      "./data/input/input000.txt",
      "./data/input/input001.txt",
      "./data/input/input002.txt",
      "./data/input/input003.txt",
      "./data/input/input004.txt",
      "./data/input/input005.txt",
      "./data/input/input006.txt",
      "./data/input/input007.txt",
      "./data/input/input008.txt",
      "./data/input/input009.txt",
      "./data/input/input010.txt",
      "./data/input/input011.txt",
      "./data/input/input012.txt"
    };

    final String[] outputFiles = {
      "./data/output/output000.txt",
      "./data/output/output001.txt",
      "./data/output/output002.txt",
      "./data/output/output003.txt",
      "./data/output/output004.txt",
      "./data/output/output005.txt",
      "./data/output/output006.txt",
      "./data/output/output007.txt",
      "./data/output/output008.txt",
      "./data/output/output009.txt",
      "./data/output/output010.txt",
      "./data/output/output011.txt",
      "./data/output/output012.txt"
    };

    final Analyzer analyzer = new MessageAnalyzer(Set.of(
      new RemoveWarrantyEventExecutor()
    ));

    for(int i = 0; i <= outputFiles.length - 1; ++i) {
      final Path inputPath = Paths.get(inputFiles[i]);
      final Path outputPath = Paths.get(outputFiles[i]);

      final List<String> inputLines = Files.readAllLines(inputPath);
      final List<String> outputLines = Files.readAllLines(outputPath);

      final String output = analyzer.execute(inputLines);

      if(output.equals(outputLines.get(0))) {
        LOGGER.info(MessageFormat.format("Test {0}/{1} - Passed ✔", i + 1, outputFiles.length));
      }
      else {
        LOGGER.info(MessageFormat.format("Test {0}/{1} - Failed ❌", i + 1, outputFiles.length));
      }
    }
  }
}
