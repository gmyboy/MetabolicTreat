package com.shwootide.metabolictreat.fragment.Common;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.event.MessageEvent;

/**
 * 帮助
 * Created by GMY on 2015/12/17 16:07.
 * Contact me via email igmyboy@gmail.com.
 */
public class HelpFragment extends MenuFragment {
    @Override
    public int bindViewById() {
        return R.layout.frag_help;
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        super.onEventMainThread(event);
    }
}
