package fr.formation.kanban.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.kanban.factory.KanbanFactory;

public class KanbanServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("kanban", KanbanFactory.getInstance());
		final Object sessionUsername = request.getSession()
				.getAttribute("username");
		if (sessionUsername != null) {
			// L'utilisateur a bien enregistré son nom, on peut l'afficher dans
			// la page.
			request.setAttribute("username", sessionUsername);
		} else {
			request.removeAttribute("username");
		}
		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/dashboard.jsp")
				.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String username = request.getParameter("username");
		if (username != null && !username.isEmpty()) {
			request.getSession().setAttribute("username", username);
		} else {
			// TODO:remonter l'erreur à l'utilisateur + logger.
		}
		this.doGet(request, response);
	}
}
