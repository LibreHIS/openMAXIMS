package ims.framework;

import java.util.LinkedList;

public class WorkQueue
{
    private final PoolWorker[] threads;
    private final LinkedList<Runnable> queue;
    private int count = 0;

    public WorkQueue()
    {
    	this(1);
    }
    public WorkQueue(int nThreads)
    {
        queue = new LinkedList<Runnable>();
        threads = new PoolWorker[nThreads];
        
        for (int i = 0; i < nThreads; i++) 
        {
            threads[i] = new PoolWorker();            
            threads[i].start();
        }
    }
    public int size()
    {
    	return count;
    }    
    public void execute(Runnable r) 
    {
        synchronized(queue) 
        {
        	count++;
            queue.addLast(r);            
            queue.notify();
        }
    }

    private class PoolWorker extends Thread 
    {
        public void run() 
        {
            Runnable r;

            while (true) 
            {
                synchronized(queue) 
                {
                    while (queue.isEmpty()) 
                    {
                        try
                        {
                            queue.wait();
                        }
                        catch (InterruptedException ignored)
                        {
                        }
                    }

                    r = queue.removeFirst();
                    count--;
                }

                try 
                {
                    r.run();
                }
                catch (RuntimeException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
}

