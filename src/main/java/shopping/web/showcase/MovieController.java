package shopping.web.showcase;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import shopping.domain.product.Movie;
import shopping.repositories.product.MovieRepository;
import shopping.web.config.Routes;


@Controller
@RequestMapping(Routes.PRODUCT_MOVIE)
public class MovieController {
	
	public static final String MODEL_NAME = "movie";
	public static final String ALERT_CODE = "alertCode";
	
	public static final String VIEW_LIST = Routes.PRODUCT_MOVIE + "/movie-list"; 
	public static final String VIEW_SHOW = Routes.PRODUCT_MOVIE + "/movie-show"; 
	public static final String VIEW_FORM = Routes.PRODUCT_MOVIE + "/movie-form"; 
	
	
	@Inject MovieRepository movieRepository;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model model) {
		
		model.addAttribute("movies", movieRepository.findAll());
		
		return VIEW_LIST;
	}

	@RequestMapping(value="/{movie}", method=RequestMethod.GET)
	public String show(@PathVariable Movie movie) {
		return VIEW_SHOW;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String newForm(Model model) {
		
		model.addAttribute(MODEL_NAME, new Movie());
		
		return VIEW_FORM;
	}
	
	@RequestMapping(method=RequestMethod.POST)
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
	
	@RequestMapping(value="edit/{movie}", method=RequestMethod.GET)
	public String editForm(@PathVariable Movie movie) {
		return VIEW_FORM;
	}
	
	@RequestMapping(value="/{movie}", method=RequestMethod.PUT)
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
	
	@RequestMapping(value="/{movie}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Movie movie) {
		
		movieRepository.delete(movie);
		
		return Routes.REDIRECT + Routes.PRODUCT_MOVIE;
	}
	
}
