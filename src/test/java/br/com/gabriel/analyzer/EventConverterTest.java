package br.com.gabriel.analyzer;

import br.com.gabriel.analyzer.events.ProponentEvent;
import br.com.gabriel.analyzer.events.ProposalEvent;
import br.com.gabriel.analyzer.events.WarrantyEvent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@DisplayName("Event converter test")
public class EventConverterTest {

  Stream<String> data;

  @BeforeEach
  void setUp() {
    String[] rawData = {
      "72ff1d14-756a-4549-9185-e60e326baf1b,proposal,created,2019-11-11T14:28:01Z,80921e5f-4307-4623-9ddb-5bf826a31dd7,1141424.0,240",
      "af744f6d-d5c0-41e9-b04f-ee524befa425,warranty,added,2019-11-11T14:28:01Z,80921e5f-4307-4623-9ddb-5bf826a31dd7,31c1dd83-8fb7-44ff-8cb7-947e604f6293,3245356.0,DF",
      "450951ee-a38d-475c-ac21-f22b4566fb29,warranty,added,2019-11-11T14:28:01Z,80921e5f-4307-4623-9ddb-5bf826a31dd7,c8753500-1982-4003-8287-3b46c75d4803,3413113.45,DF",
      "66882b68-baa4-47b1-9cc7-7db9c2d8f823,proponent,added,2019-11-11T14:28:01Z,80921e5f-4307-4623-9ddb-5bf826a31dd7,3f52890a-7e9a-4447-a19b-bb5008a09672,Ismael Streich Jr.,42,62615.64,true",
      "fc0989d7-ee41-46e3-8d15-448ebee23394,proponent,added,2019-11-11T14:28:01Z,80921e5f-4307-4623-9ddb-5bf826a31dd7,542c49bc-fde5-44f5-92c0-3d2a3d2d92a2,Mrs. Peter Wisozk,41,67745.71,false"
    };
    this.data = Stream.of(rawData).flatMap(data -> Arrays.stream(data.split(",")));
  }

  @Test
  @DisplayName("Should have 5 events")
  void test1() {
    var events = StreamSupport.stream(
      new EventSpliterator(this.data),
      false
    ).toList();

    Assertions.assertThat(events).hasSize(5);

  }

  @Test
  @DisplayName("Should contain 2 warranty")
  void test2() {
    var events = StreamSupport.stream(new EventSpliterator(this.data), false)
      .filter(WarrantyEvent.class::isInstance)
      .toList();

    Assertions.assertThat(events).hasOnlyElementsOfType(WarrantyEvent.class);
  }

  @Test
  @DisplayName("Should contain 1 proposal")
  void test3() {

    var events = StreamSupport.stream(new EventSpliterator(this.data), false)
      .filter(ProposalEvent.class::isInstance)
      .toList();

    Assertions.assertThat(events).hasOnlyElementsOfType(ProposalEvent.class);
  }

  @Test
  @DisplayName("Should contain 2 proponent")
  void test4() {

    var events = StreamSupport.stream(new EventSpliterator(this.data), false)
      .filter(ProponentEvent.class::isInstance)
      .toList();

    Assertions.assertThat(events).hasOnlyElementsOfType(ProponentEvent.class);
  }


}
