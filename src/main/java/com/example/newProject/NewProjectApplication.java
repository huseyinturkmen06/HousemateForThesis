package com.example.newProject;

import com.example.newProject.Services.password.SecurityConfig;
import org.python.util.PythonInterpreter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Properties;

@SpringBootApplication
//@EnableSwagger2
@Import(SecurityConfig.class)
public class NewProjectApplication {

	public static void main(String[] args) {



		SpringApplication.run(NewProjectApplication.class, args);

//		PythonInterpreter pythonInterpreter=new PythonInterpreter();
//		pythonInterpreter.exec("print('bu bir python kodu')");
////		pythonInterpreter.exec("import pandas" );
//		pythonInterpreter.exec("import pandas as pd" );
//		pythonInterpreter.exec("df = pd.read_csv(\"/veriler.csv\")" );
//		pythonInterpreter.exec("print(df.shape)" );
//		System.out.println("java kodu");

//		PythonInterpreter.initialize(System.getProperties(),System.getProperties(), new String[0]);
//		PythonInterpreter interp = new PythonInterpreter();
//		interp.execfile("C:\\Users\\husey\\Desktop\\Housemate-Finding-Application-With-Java-Spring\\Spring Application\\src\\main\\java\\com\\example\\newProject\\deneme.py");


//		Properties properties = System.getProperties();
//		properties.put("C:\\Users\\husey\\AppData\\Local\\Programs\\Python\\Python38",
//				"C:\\Users\\husey\\Desktop\\Housemate-Finding-Application-With-Java-Spring\\Spring Application\\src\\main\\java\\com\\example\\newProject\\deneme.py");
//		PythonInterpreter.initialize(System.getProperties(),System.getProperties(), new String[0]);
//		PythonInterpreter interp = new PythonInterpreter();
//		interp.execfile("C:\\Users\\husey\\Desktop\\Housemate-Finding-Application-With-Java-Spring\\Spring Application\\src\\main\\java\\com\\example\\newProject\\deneme.py");


	}

}
