package ro.fortech.bookshelf.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ro.fortech.bookshelf.domain.Book;
import ro.fortech.bookshelf.service.BookService;
import ro.fortech.bookshelf.service.SecurityService;
import ro.fortech.bookshelf.service.ValidationException;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private BookService bookService;

	@RequestMapping("add")
	public ModelAndView renderAdd() {
		ModelAndView modelAndView = new ModelAndView("book/add");
		modelAndView.addObject("book", new Book());
		return modelAndView;
	}

	@RequestMapping("edit")
	public ModelAndView renderEdit(long id) {
		ModelAndView modelAndView = new ModelAndView("book/add");
		modelAndView.addObject("book", bookService.get(id));
		return modelAndView;
	}

	@RequestMapping("delete")
	public ModelAndView delete(long id) {
		ModelAndView modelAndView = new ModelAndView();
		bookService.delete(id);
		modelAndView.setView(new RedirectView("/book"));
		return modelAndView;
	}

	@RequestMapping("save")
	public ModelAndView save(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult) {
		ModelAndView modelAndView = null;
		boolean hasErros = false;
		if (!bindingResult.hasErrors()) {
			try {
				bookService.save(book);

				modelAndView = new ModelAndView();
				modelAndView.setView(new RedirectView(""));
			} catch (ValidationException ex) {
				for (String msg : ex.getCauses()) {
					bindingResult.addError(new ObjectError("book", msg));
				}
				hasErros = true;
			}
		} else {
			hasErros = true;
		}

		if (hasErros) {
			modelAndView = new ModelAndView("book/add");
			modelAndView.addObject("book", book);
			modelAndView.addObject("errors", bindingResult.getAllErrors());
		}

		return modelAndView;
	}

	@RequestMapping("")
	public ModelAndView list() throws Exception {
		ModelAndView modelAndView = new ModelAndView("book/list");
		modelAndView.addObject("books", bookService.listAll());
		modelAndView.addObject("currentUser", securityService.getCurrentUser());

		return modelAndView;
	}
}
