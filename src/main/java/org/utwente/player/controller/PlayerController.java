package org.utwente.player.controller;

import java.util.List;

import org.utwente.player.model.Player;
import org.utwente.player.view.PlayerView;

public class PlayerController {
  private List<Player> playerModel;
  private PlayerView playerView;

  public PlayerController(List<Player> playerModel, PlayerView playerView) {
    this.playerModel = playerModel;
    this.playerView = playerView;
  }
}
