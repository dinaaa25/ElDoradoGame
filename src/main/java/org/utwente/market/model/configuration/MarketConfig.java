package org.utwente.market.model.configuration;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketConfig {
  private List<MarketCardConfig> available;
  private List<MarketCardConfig> reserve;
}
