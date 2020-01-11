package io.github.handofgod94.service.parser;

import io.github.handofgod94.BadgeUtility;

import java.io.File;

public class ReportParserFactory {
  public static final String CSV_EXT = "csv";

  /**
   * Factory method to generate an instance of ReportParser.
   * The ReportParser can be used to parse different formats of jacoco report
   * @param file jacoco report file which needs to be parsed.
   * @return ReportParser based on the extension of the jacoco file.
   * @throws IllegalArgumentException if jacoco report file is invalid.
   * @throws UnsupportedOperationException if file with invalid extension is provided.
   */
  public static ReportParser create(File file) {
    String ext =
        BadgeUtility.getFileExt(file)
          .orElseThrow(() -> new IllegalArgumentException("Invalid Jacoco file provided"));

    if (ext.equals(CSV_EXT)) {
      return new CsvReportParser();
    } else {
      throw new UnsupportedOperationException("Invalid extension for report");
    }
  }
}