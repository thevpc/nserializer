package net.thevpc.lib.nserializer.impl;

import net.thevpc.nuts.text.NMsg;
import net.thevpc.lib.nserializer.api.StoreProgressMonitor;

import java.util.ArrayList;
import java.util.List;

public class StoreProgressMonitorHelper implements StoreProgressMonitor {
    public static final StoreProgressMonitor SILIENT = new StoreProgressMonitor() {
        @Override
        public void onProgress(double progress, NMsg message) {

        }
    };
    private StoreProgressMonitor mon = SILIENT;
    private List<StoreProgressMonitor> mons=new ArrayList<>();

    public void addProgressMonitor(StoreProgressMonitor m){
        if(m!=null){
            mons.add(m);
        }
        if(mons.isEmpty()){
            mon =SILIENT;
        }else if(mons.size()==1){
            mon =mons.get(0);
        }else{
            mon =new StoreProgressMonitor() {
                @Override
                public void onProgress(double progress, NMsg message) {
                    for (StoreProgressMonitor mon : mons) {
                        mon.onProgress(progress, message);
                    }
                }
            };
        }
    }

    @Override
    public void onProgress(double progress, NMsg message) {
        mon.onProgress(progress, message);
    }
}
