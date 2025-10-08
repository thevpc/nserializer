package net.thevpc.lib.nserializer.impl;

import net.thevpc.lib.nserializer.api.IOLogger;
import net.thevpc.lib.nserializer.api.IOLoggerNode;
import net.thevpc.nuts.text.NMsg;

import java.util.List;
import java.util.concurrent.Callable;

public class IOLoggerNodes {
    private static ThreadLocal<IOLoggerNode> current = new ThreadLocal<>();
    public  static void set(IOLoggerNode c){
        current.set(c==null?NULL:c);
    }
    public  static IOLoggerNode get(){
        IOLoggerNode t = current.get();
        return t==null?NULL:t;
    }
    public static final IOLoggerNode NULL=new IOLoggerNodeNull();
    public static IOLoggerNode of(IOLogger... li) {
        if(li==null){
            return NULL;
        }
        List<IOLoggerNode> all=new java.util.ArrayList<>();
        for (IOLogger a : li) {
            if(a==null|| (a instanceof AbstractIOLoggerNode && ((AbstractIOLoggerNode) a).isBlank())){
                //
            }else {
                if(a instanceof AbstractIOLoggerNode){
                    all.addAll(java.util.Arrays.asList(((AbstractIOLoggerNode)a).linearize()));
                }else{
                    all.add(of(a));
                }
            }
        }
        if(all.isEmpty()){
            return NULL;
        }
        if(all.size()==1){
            return all.get(0);
        }
        return new IOLoggerNodeList(all.toArray(new IOLoggerNode[0]));
    }

    public static IOLoggerNode of(IOLogger a) {
        if (a == null) {
            return NULL;
        }
        if (a instanceof IOLoggerNode) {
            return (IOLoggerNode) a;
        }
        return new IOLoggerNodeImpl(a);
    }



    private static class IOLoggerNodeList extends AbstractIOLoggerNode {
        private IOLoggerNode[] nodes;

        public IOLoggerNodeList(IOLoggerNode[] nodes) {
            this.nodes = nodes;
        }

        @Override
        protected IOLoggerNode[] linearize() {
            return nodes;
        }

        @Override
        public void log(NMsg msg) {
            for (IOLoggerNode node : nodes) {
                node.log(msg);
            }
        }
    }
    private static class IOLoggerNodeImpl extends AbstractIOLoggerNode {
        private IOLogger lo;

        public IOLoggerNodeImpl(IOLogger lo) {
            this.lo = lo;
        }

        @Override
        public void log(NMsg msg) {
            lo.log(msg);
        }
    }

    private static class IOLoggerNodeNull extends AbstractIOLoggerNode {
        public IOLoggerNodeNull() {
        }

        @Override
        public void log(NMsg msg) {
        }
    }

    private static abstract class AbstractIOLoggerNode implements IOLoggerNode {
        protected IOLoggerNode[] linearize() {
            return new IOLoggerNode[]{this};
        }
        protected boolean isBlank() {
            if (this instanceof IOLoggerNodeNull) {
                return true;
            }
            return false;
        }

        public void runWith(Runnable run){
            if(run==null){
                return;
            }
            IOLoggerNode cc = get();
            set(this);
            try{
                run.run();
            }finally {
                set(cc);
            }
        }

        public <T> T callWith(Callable<T> run) throws Exception {
            if(run==null){
                return null;
            }
            IOLoggerNode cc = get();
            set(this);
            try{
                return run.call();
            }finally {
                set(cc);
            }
        }

        @Override
        public IOLoggerNode add(IOLogger other) {
            if(other==null|| (other instanceof AbstractIOLoggerNode && ((AbstractIOLoggerNode) other).isBlank())){
                return this;
            }
            if(isBlank()){
                return of(other);
            }
            List<IOLoggerNode> all=new java.util.ArrayList<>();
            all.addAll(java.util.Arrays.asList(linearize()));
            if(other instanceof AbstractIOLoggerNode){
                all.addAll(java.util.Arrays.asList(((AbstractIOLoggerNode)other).linearize()));
            }else{
                all.add(of(other));
            }
            if(all.isEmpty()){
                return NULL;
            }
            if(all.size()==1){
                return all.get(0);
            }
            return new IOLoggerNodeList(all.toArray(new IOLoggerNode[0]));
        }
    }
}
