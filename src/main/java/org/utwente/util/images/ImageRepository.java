package org.utwente.util.images;

import org.utwente.CaveCoin.CaveCoinImageLoader;
import org.utwente.Tile.TileImageLoader;

public class ImageRepository {
  public static CaveCoinImageLoader caveCoinLoader;
  public static TileImageLoader tileImageLoader;

  public static CaveCoinImageLoader getCaveCoinLoader() {
    if (caveCoinLoader == null) {
      caveCoinLoader = new CaveCoinImageLoader();
    }
    return caveCoinLoader;
  }

  public static TileImageLoader getTileImageLoader() {
    if (tileImageLoader == null) {
      tileImageLoader = new TileImageLoader();
    }
    return tileImageLoader;
  }

}
