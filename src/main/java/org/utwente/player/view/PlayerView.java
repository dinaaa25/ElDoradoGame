package org.utwente.player.view;

import java.util.List;

import org.utwente.player.model.Player;

public interface PlayerView {
  void showCards();

  void addPlayCardListener(ActionListener listener);

  void setPlayers(List<Player> players);
}
