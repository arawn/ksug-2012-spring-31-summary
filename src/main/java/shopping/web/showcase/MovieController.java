package shopping.web.showcase;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import shopping.domain.product.Movie;
import shopping.repositories.product.MovieRepository;
import shopping.web.config.Routes;


@Controller
public class MovieController {
	
	public static final String MODEL_NAME = "movie";
	public static final String ALERT_CODE = "alertCode";
	
	public static final String VIEW_LIST = Routes.PRODUCT_MOVIE + "/movie-list"; 
	public static final String VIEW_SHOW = Routes.PRODUCT_MOVIE + "/movie-show"; 
	public static final String VIEW_FORM = Routes.PRODUCT_MOVIE + "/movie-form"; 
	
	
	@Inject MovieRepository movieRepository;
	
	
	public String list(Model model) {
		
		model.addAttribute("movies", movieRepository.findAll());
		
		return VIEW_LIST;
	}

	public String show(@PathVariable Movie movie) {
		return VIEW_SHOW;
	}
	
	public String newForm(Model model) {
		
		model.addAttribute(MODEL_NAME, new Movie());
		
		return VIEW_FORM;
	}
	
	public String newMovie(@Valid @ModelAttribute(MODEL_NAME) Movie movie
						 , BindingResult bindingResult
						 , RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			return VIEW_FORM;
		}

		movieRepository.save(movie);
		
		redirectAttributes.addAttribute(MODEL_NAME, movie);
		redirectAttributes.addFlashAttribute(ALERT_CODE, "movie.new.succeed");
		
		return Routes.REDIRECT + Routes.PRODUCT_MOVIE + "/{movie}";
	}
	
	public String editForm(@PathVariable Movie movie) {
		return VIEW_FORM;
	}
	
	public String editMovie(@Valid @ModelAttribute(MODEL_NAME) Movie movie
						  , BindingResult bindingResult
						  , RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			return VIEW_FORM;
		}
		
		movieRepository.save(movie);
		
		redirectAttributes.addAttribute(MODEL_NAME, movie);
		redirectAttributes.addFlashAttribute(ALERT_CODE, "movie.edit.succeed");
		
		return Routes.REDIRECT + Routes.PRODUCT_MOVIE + "/{movie}";
	}
	
	public String delete(@PathVariable Movie movie) {
		
		movieRepository.delete(movie);
		
		return Routes.REDIRECT + Routes.PRODUCT_MOVIE;
	}
	
}
