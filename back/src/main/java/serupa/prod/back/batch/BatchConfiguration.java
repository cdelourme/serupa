//package serupa.prod.back.batch;
//
//import javax.sql.DataSource;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableBatchProcessing
//public class BatchConfiguration extends DefaultBatchConfigurer{
//    
//   @Autowired
//   private JobBuilderFactory jobs;
//
//   @Autowired
//   private StepBuilderFactory steps;
//   
//   @Override
//   public void setDataSource(DataSource dataSource) {
//       // override to do not set datasource even if a datasource exist.
//       // initialize will use a Map based JobRepository (instead of database)
//   }
//   
//   @Bean
//   public Step stepOne(){
//       return steps.get("stepOne")
//               .tasklet(new MyTaskOne())
//               .build();
//   }
//    
//   @Bean
//   public Step stepTwo(){
//       return steps.get("stepTwo")
//               .tasklet(new MyTaskTwo())
//               .build();
//   }   
//    
//   @Bean
//   public Job demoJob(){
//       return jobs.get("demoJob")
//               .incrementer(new RunIdIncrementer())
//               .start(stepOne())
//               .next(stepTwo())
//               .build();
//   }
//}
