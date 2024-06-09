package org.utwente.market;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.shaded.org.apache.commons.io.output.ByteArrayOutputStream;
import org.utwente.market.controller.MarketOrderEvent;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Market;
import org.utwente.market.view.MarketCli;
import org.utwente.util.EventHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.*;
import java.util.*;

public class MarketCliTest {

  MarketCli marketView;
  ByteArrayOutputStream outContent;
  PrintStream testStream;
  Scanner mockScanner;
  // @Mock
  // EventHandler<InputEvent> mockEventHandler;

  @BeforeEach
  public void setup() {
    outContent = new ByteArrayOutputStream();
    mockScanner = new Scanner(System.in);
    testStream = new PrintStream(outContent);
    marketView = new MarketCli(testStream, mockScanner);
    // marketView.setInputEventHandler(mockEventHandler);
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
  public void testSetOnInput() {
    // EventHandler<InputEvent> mockEventHandler = new EventHandler<InputEvent>() {

    // @Override
    // public void handle(InputEvent event) {
    // }

    // };
    // marketView.setOnInput(mockEventHandler);
    // assertEquals(mockEventHandler, marketView.getInputEventHandler());
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
    marketView.displayPurchaseResult(new Card(CardType.Abenteurerin));
    assertTrue(new String(outContent.toByteArray()).contains("ABENTEURERIN"));
  }

  @Test
  public void testDisplayError() {
    marketView.displayError("blue");
    assertTrue(new String(outContent.toByteArray()).contains("blue"));
  }

  @Test
  public void testExit() {
    marketView.exit();
    assertTrue(marketView.isExit());
  }
}
