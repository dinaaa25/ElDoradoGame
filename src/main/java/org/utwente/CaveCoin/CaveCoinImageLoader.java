package org.utwente.CaveCoin;

import org.utwente.util.images.ImageLoader;

public class CaveCoinImageLoader extends ImageLoader<CaveCoinType> {

    @Override
    protected Class<CaveCoinType> getEnumType() {
        return CaveCoinType.class;
    }

    @Override
    protected String getImagePath(CaveCoinType coin, int power) {
        return String.format("/images/cavecoins/%s-%d.png", coin.name(), power);
    }
}