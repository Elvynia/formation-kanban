package fr.formation.kanban.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.kanban.dao.TaskDao;
import fr.formation.kanban.model.Task;

public class DetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DetailsServlet.class);

	private TaskDao taskDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.taskDao = new TaskDao();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final String strId = request.getParameter("taskId");
		try {
			final int taskId = Integer.parseInt(strId);
			final Task task = this.taskDao.read(new Task(taskId));
			if (task != null) {
				request.setAttribute("task", task);
				this.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/details.jsp")
						.forward(request, response);
				return;
			}
		} catch (final NumberFormatException e) {
			LOGGER.trace("Details page requested without a valid task id.", e);
		}
		response.sendRedirect(this.getServletContext().getContextPath());
	}
}
