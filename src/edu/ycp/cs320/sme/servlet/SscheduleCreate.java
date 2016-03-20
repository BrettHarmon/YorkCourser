package edu.ycp.cs320.sme.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.sme.model.Schedule;
import edu.ycp.cs320.sme.model.Student;

public class SscheduleCreate extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
	  	
	  req.getRequestDispatcher("/_view/newSchedule.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
	  
    // Decode form parameters and dispatch to controller
    String errorMessage = null;
    String name = null;
    String semester = null;
    
    name = req.getParameter("name");
    semester = req.getParameter("semester");
    
    //this really doesn't work yet. Not a persistent user
    //send them to edit this schedule
    if(name != null){
    	Student s = new Student();
    	Schedule schedule = new Schedule();
    	schedule.setName(name);
    	schedule.setSemester(semester);
    	s.addSchedule(schedule);
    	req.setAttribute("student", s);
    	
    	//TODO: See if this is actually running the doGet portion or skips to the post
        resp.sendRedirect("/studentEdit");
    }else{
    	errorMessage = "Please include a name";
    }

    // Add result objects as request attributes
    req.setAttribute("errorMessage", errorMessage);
    

    // Forward to view to render the result HTML document
    req.getRequestDispatcher("/_view/newSchedule.jsp").forward(req, resp);
  }

}
