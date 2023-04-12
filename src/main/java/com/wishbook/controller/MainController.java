package com.wishbook.controller;

import com.wishbook.models.Wish;
import com.wishbook.models.WishList;
import com.wishbook.repository.UserRepository;
import com.wishbook.models.User;
import com.wishbook.repository.WishListRepository;
import com.wishbook.repository.WishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Blob;
import java.util.List;

@Controller
public class MainController {

    private final UserRepository userRepository;
    private final WishListRepository wishListRepository;
    private final WishRepository wishRepository;

    public MainController(UserRepository userRepository, WishListRepository wishListRepository, WishRepository wishRepository){
        this.userRepository = userRepository;
        this.wishListRepository = wishListRepository;
        this.wishRepository = wishRepository;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    //POST MAPPING FOR USER LOGIN
    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password, Model model){
        //Check if user with mail already exists
        if(!userRepository.loginVerification(password, email)){
            model.addAttribute("errorMessage", "Email or password invalid");
            return "home";
        }

        return "overview";
    }


    //POST MAPPING FOR REGISTERING USER
    @PostMapping("/register")
    public String registerUser(@RequestParam("fname") String fname,
                               @RequestParam("lname") String lname,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password, Model model){
        //Check if user with mail already exists
        if(!userRepository.checkIfUserExists(email)){
            User user = new User(fname, lname, email, password);
            userRepository.addUser(user);
        }else{
            model.addAttribute("errorMessage", "Email already in use");
            return "register";
        }


        return "redirect:/";
    }

    @GetMapping("/overview/{userID}")
    public String showOverviewPage(@PathVariable("userID") Integer userID, Model model){
        List<WishList> listOfWishlists = wishListRepository.getWishListsByUserID(userID);
        model.addAttribute("wishlists", listOfWishlists);
        return "overview";
    }

    @GetMapping("/profile/{wishlistID}")
    public String showProfilePage(@PathVariable("wishlistID") Integer wishlistID, Model model){
        return "profile-page";
    }

    //POST MAPPING FOR UPDATING WISHLIST
    @PostMapping("/update-wishlist")
    public String updateWishList(@RequestParam("wishlist-name") String wishListName,
                                 @RequestParam("occasion") String occasion,
                                 @RequestParam("cover-pic")Blob coverPic){
        WishList wishList = new WishList();
        wishList.setList_name(wishListName);
        wishList.setOccasion(occasion);
        wishList.setCover_pic(coverPic);
        wishListRepository.updateWishList(wishList);
        return "redirect:/";
    }

    //POST MAPPING FOR CREATING WISHLIST
    @PostMapping("/create-wishlist")
    public String createWishlist(@RequestParam("user-id") int userID,
                                 @RequestParam("wishlist-name") String wishListName,
                                 @RequestParam("occasion") String occasion,
                                 @RequestParam("cover-pic")Blob coverPic){

        WishList wishList = new WishList(userID, wishListName, occasion, coverPic);
        wishListRepository.addWishList(wishList);

        return "redirect:/";
    }

}
