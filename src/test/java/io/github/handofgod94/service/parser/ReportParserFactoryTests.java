package io.github.handofgod94.service.parser;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportParserFactoryTests {

  @Test
  void create_returns_CSVReportParser_for_jacoco_csv_report() {
    File file = mock(File.class);
    when(file.getName()).thenReturn("reprot.csv");
    ReportParser parser = ReportParserFactory.create(file);
    assertTrue(parser instanceof CsvReportParser);
  }

  @Test
  void create_throws_NotImplementedException_for_invalid_jacoco_report_file_ext() {
    File file = mock(File.class);
    when(file.getName()).thenReturn("foo.bar");
    assertThrows(UnsupportedOperationException.class, () -> {
      ReportParserFactory.create(file);
    });
  }
}