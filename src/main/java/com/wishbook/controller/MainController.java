package com.wishbook.controller;

import com.wishbook.models.Wish;
import com.wishbook.models.WishList;
import com.wishbook.repository.UserRepository;
import com.wishbook.models.User;
import com.wishbook.repository.WishListRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Blob;
import java.util.List;

@Controller
public class MainController {

    private UserRepository userRepository;
    private WishListRepository wishListRepository;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/overview/{userID}")
    public String showOverviewPage(@PathVariable("userID") Integer userID, Model model){
        User user = userRepository.findUserByID(userID);
        List<WishList> listOfWishlists = wishListRepository.getWishListsByUserID(userID);
        model.addAttribute("user", user);
        model.addAttribute("wishlists", listOfWishlists);
        return "overview";
    }

    @GetMapping("/profile/{wishlistID}")
    public String showProfilePage(@PathVariable("wishlistID") Integer wishlistID, Model model){


        return "profile-page";
    }


    //POST MAPPING FOR REGISTERING USER
    @PostMapping("/register")
    public String registerUser(@RequestParam("fname") String fname,
                               @RequestParam("lname") String lname,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password, Model model){
        //Check if user with mail already exists
        if(!userRepository.checkIfUserExists(email)){
            User newUser = new User(fname, lname, email, password);

            //add user to DB
            userRepository.addUser(newUser);
        }else{
            String errorMessage = "User is already registered!";
            model.addAttribute("errorMessage", errorMessage);
        }
        return "redirect:/";
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
