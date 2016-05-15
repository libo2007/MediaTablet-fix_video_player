package com.jiaying.mediatablet.net.state.stateswitch;

import android.softfan.dataCenter.DataCenterRun;
import android.softfan.dataCenter.task.DataCenterTaskCmd;

import com.jiaying.mediatablet.net.signal.RecSignal;
import com.jiaying.mediatablet.net.state.RecoverState.RecordState;
import com.jiaying.mediatablet.net.thread.ObservableZXDCSignalListenerThread;

/**
 * Created by hipil on 2016/4/13.
 */
public abstract class AbstractState {
    protected TabletStateContext tabletStateContext;

    public void setTabletStateContext(TabletStateContext tabletStateContext) {
        this.tabletStateContext = tabletStateContext;
    }

    abstract void handleMessage(RecordState recordState,ObservableZXDCSignalListenerThread listenerThread,DataCenterRun dataCenterRun, DataCenterTaskCmd cmd,RecSignal recSignal);
}
