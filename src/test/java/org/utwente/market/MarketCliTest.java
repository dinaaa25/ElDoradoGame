package org.utwente.market;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.shaded.org.apache.commons.io.output.ByteArrayOutputStream;
import org.utwente.market.controller.InputEvent;
import org.utwente.market.controller.MarketOrderEvent;
import org.utwente.market.model.Market;
import org.utwente.market.view.MarketCli;
import org.utwente.util.EventHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.*;

public class MarketCliTest {

  MarketCli marketView;
  ByteArrayOutputStream outContent;
  PrintStream testStream;

  @BeforeEach
  public void setup() {
    outContent = new ByteArrayOutputStream();
    testStream = new PrintStream(outContent);
    marketView = new MarketCli(testStream);
  }

  @Test
  public void testDefaultConstructor() {
    MarketCli cli = new MarketCli();
    assertEquals(System.out, cli.getStream());
  }

  @Test
  public void testSetMarket() {
    Market mockMarket = new Market();
    this.marketView.setMarket(mockMarket);
    assertEquals(mockMarket, marketView.getMarket());
  }

  @Test
  public void testSetOnOrder() {
    EventHandler<MarketOrderEvent> mockEventHandler = new EventHandler<MarketOrderEvent>() {

      @Override
      public void handle(MarketOrderEvent event) {

      }
    };

    marketView.setOnOrder(mockEventHandler);
    assertEquals(mockEventHandler, marketView.getEventHandler());
  }

  @Test
  public void testSetOnInput() {
    EventHandler<InputEvent> mockEventHandler = new EventHandler<InputEvent>() {

      @Override
      public void handle(InputEvent event) {
      }

    };
    marketView.setOnInput(mockEventHandler);
    assertEquals(mockEventHandler, marketView.getInputEventHandler());
  }

  @Test
  public void testDisplayMarket() {
    marketView.setMarket(new Market());
    marketView.displayMarket();
    assertTrue(new String(outContent.toByteArray()).contains("welcome to el dorado market!"));
  }

  @Test
  public void testGetOrder() {
  }

  @Test
  public void testDisplayPurchaseResult() {

  }

  @Test
  public void testDisplayError() {

  }

  @Test
  public void testExit() {
    marketView.exit();
    assertTrue(marketView.isExit());
  }
}
