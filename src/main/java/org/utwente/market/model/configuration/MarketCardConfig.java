package org.utwente.market.model.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class MarketCardConfig {
  String name;
  String powerType;
  int power;
  int price;
  int quantity;
}
