package com.jiaying.mediatablet.net.state.stateswitch;

import android.softfan.dataCenter.DataCenterException;
import android.softfan.dataCenter.DataCenterRun;
import android.softfan.dataCenter.task.DataCenterTaskCmd;
import android.softfan.util.textUnit;

import com.jiaying.mediatablet.entity.ServerTime;
import com.jiaying.mediatablet.net.signal.RecSignal;
import com.jiaying.mediatablet.net.state.RecoverState.RecordState;
import com.jiaying.mediatablet.net.thread.ObservableZXDCSignalListenerThread;

import java.util.HashMap;

/**
 * Created by hipil on 2016/4/13.
 */
public class WaitingForStartState extends AbstractState {
    private static WaitingForStartState waitingForStartState = null;

    private WaitingForStartState() {
    }

    public static WaitingForStartState getInstance() {
        if (waitingForStartState == null) {
            waitingForStartState = new WaitingForStartState();
        }
        return waitingForStartState;
    }

    @Override
    public synchronized void handleMessage(RecordState recordState, ObservableZXDCSignalListenerThread listenerThread, DataCenterRun dataCenterRun, DataCenterTaskCmd cmd, RecSignal recSignal) {
        switch (recSignal) {

            case TIMESTAMP:
                if ("timestamp".equals(cmd.getCmd())) {
                    ServerTime.curtime = Long.parseLong(textUnit.ObjToString(cmd.getValue("t")));
                }
                break;

            case START:
                //记录状态
                recordState.recCollection();

                //发送信号
                listenerThread.notifyObservers(RecSignal.START);

                //切换状态
                TabletStateContext.getInstance().setCurrentState(CollectionState.getInstance());

                break;

        }

    }



}
