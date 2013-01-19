/** 
* 
 */ 
package demo.quartz; 




import org.quartz.Job; 
import org.quartz.JobExecutionContext; 
import org.quartz.JobExecutionException; 

/** 
 * @author XKF40434 
 * 
 */ 
public class HelloJob implements Job 
{ 
    

        public void execute(JobExecutionContext context) 
        throws JobExecutionException { 

        // Say Hello to the World and display the date/time 
          System.out.println("你好"); 
   
    } 

} 