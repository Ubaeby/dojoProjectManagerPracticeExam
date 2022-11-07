package com.codingdojo.manager.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.manager.models.Assignment;
import com.codingdojo.manager.models.Task;
import com.codingdojo.manager.models.User;
import com.codingdojo.manager.services.AssignmentService;
import com.codingdojo.manager.services.TaskService;
import com.codingdojo.manager.services.UserService;

@Controller
public class MainController {

	@Autowired
	private AssignmentService aServ;
	@Autowired
	private UserService uServ;
	@Autowired
	private TaskService tServ;
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("allUsers", uServ.findUserId(userId));
		model.addAttribute("aAssignments", aServ.assignedUser(uServ.findUserId(userId)));
		model.addAttribute("uAssignments", aServ.unassignedUser(uServ.findUserId(userId)));
		return "dashboard.jsp";
	}
	
	@RequestMapping("/dashboard/join/{assignmentId}")
	public String joinTeam(
			@PathVariable("assignmentId") Long assignmentId, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}
		
		Assignment assignment = aServ.findAssignmentId(assignmentId);
		User user = uServ.findUserId(userId);
		
		user.getAssignments().add(assignment);
		uServ.updateUser(user);
		
		model.addAttribute("allUsers", uServ.findUserId(userId));
		model.addAttribute("aAssignments", aServ.assignedUser(uServ.findUserId(userId)));
		model.addAttribute("uAssignments", aServ.unassignedUser(uServ.findUserId(userId)));
		
		return "redirect:/dashboard";
	}
	
	@RequestMapping("/dashboard/leave/{assignmentId}")
	public String leaveTeam(
			@PathVariable("assignmentId") Long assignmentId, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}
		Assignment assignment = aServ.findAssignmentId(assignmentId);
		User user = uServ.findUserId(userId);
		user.getAssignments().remove(assignment);
		uServ.updateUser(user);

		model.addAttribute("allUsers", uServ.findUserId(userId));
		model.addAttribute("aAssignments", aServ.assignedUser(uServ.findUserId(userId)));
		model.addAttribute("uAssignments", aServ.unassignedUser(uServ.findUserId(userId)));
		return "redirect:/dashboard";
	}
	
	@GetMapping("/assignments/new")
	public String assignmentPage(
			@ModelAttribute("newAssignment") Assignment newAssignment, HttpSession session,  Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}
		return "newProject.jsp";
	}
	
	@PostMapping("/assignments/new")
	public String addAssignment(
			@Valid @ModelAttribute("newAssignment") Assignment newAssignment, BindingResult result, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		if (result.hasErrors()) {
			return "newProject.jsp";
		} else {
			User user = uServ.findUserId(userId);
			Assignment assign = new Assignment(newAssignment.getTitle(), newAssignment.getDescription(), newAssignment.getDueDate(), newAssignment.getLead());
			assign.setLead(user);
			aServ.addAssign(assign);
			user.getAssignments().add(assign);
			uServ.updateUser(user);
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/assignments/edit/{id}")
	public String editPage(
			@PathVariable("id") Long id, HttpSession session,  Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		Assignment a = aServ.findAssignmentId(id);
		model.addAttribute("newAssignment", a);
		return "editProject.jsp";
	}
	
	@PutMapping("/assignments/edit/{id}")
	public String editAssignment(
			@PathVariable("id") Long id, 
			@Valid @ModelAttribute("newAssignment") Assignment newAssignment, BindingResult result, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = uServ.findUserId(userId);
		
		if (result.hasErrors()) {
			return "editProject.jsp";
		} 
		else {
			Assignment assignment = aServ.findAssignmentId(id);
			newAssignment.setUsers(assignment.getUsers());
			newAssignment.setLead(user);
			aServ.updateAssign(newAssignment);
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/assignments/{id}")
	public String showAssignment(
			@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		Assignment a = aServ.findAssignmentId(id);
		model.addAttribute("assignments", a);
		
		return "showProject.jsp";
	}
	
	// One-To-Many relationships of Task routes!
	
	@GetMapping("/assignments/{id}/tasks")
	public String showTasksPage(
			@PathVariable("id") Long id, HttpSession session, Model model,
			@ModelAttribute("task") Task newTask) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		model.addAttribute("tasks", tServ.allTask());
		model.addAttribute("assignments", aServ.findAssignmentId(id));
		return "addTask.jsp";
	}
	
	@PostMapping("/assignments/{id}/tasks")
	public String createTask(
			@PathVariable("id") Long id, HttpSession session, Model model,
			@Valid @ModelAttribute("task") Task newTask, BindingResult result) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		Long userId = (Long) session.getAttribute("userId");
		Assignment assignment = aServ.findAssignmentId(id);
		
		if (result.hasErrors()) {
			model.addAttribute("assignments", assignment);
			model.addAttribute("tasks", tServ.tasksInAssignment(id));
			return "addTask.jsp";
		}
		
		Task theTask = new Task(newTask.getDescription());
		theTask.setAssignment(assignment);
		theTask.setCreator(uServ.findUserId(userId));
		tServ.createTask(theTask);
		return "redirect:/assignments/" + id + "/tasks";                                                                                                           
	}
}
