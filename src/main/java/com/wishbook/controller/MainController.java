package com.wishbook.controller;

import com.wishbook.models.Wish;
import com.wishbook.models.WishList;
import com.wishbook.repository.UserRepository;
import com.wishbook.models.User;
import com.wishbook.repository.WishListRepository;
import com.wishbook.repository.WishRepository;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.webresources.war.WarURLConnection;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
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
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session){
        //Check if user with mail already exists
        if(!userRepository.loginVerification(password, email)){
            model.addAttribute("errorMessage", "Email or password invalid");
            return "home";
        }

        User user = userRepository.getUserByEmailAndPassword(email, password);
        session.setAttribute("user", user);

        return "redirect:/overview";
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

    @GetMapping("/overview")
    public String showOverviewPage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<WishList> listOfWishlists = wishListRepository.getWishListsByUserID(user.getId());
        session.setAttribute("wishlistLists", listOfWishlists);
        model.addAttribute("wishlists", listOfWishlists);
        return "overview";
    }

    @GetMapping("/profile")
    public String showProfilePage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        return "profile";
    }

    //POST FOR UPDATING PROFILE
    @PostMapping("/update-profile")
    public String updateProfile(@RequestParam("fname") String fname,
                                @RequestParam("lname") String lname,
                                @RequestParam("email") String email,
                                HttpSession session){
        User user = (User) session.getAttribute("user");
        user.setFname(fname);
        user.setLname(lname);
        user.setEmail(email);
        userRepository.updateUser(user);

        return"redirect:/profile";
    }

    //POST MAPPING FOR UPDATING WISHLIST
    @PostMapping("/update-wishlist")
    public String updateWishList(@RequestParam("list-name") String wishListName,
                                 @RequestParam("occasion") String occasion,
                                 @RequestParam("cover-pic")MultipartFile coverPic, HttpSession session){
        WishList wishList = (WishList) session.getAttribute("wishlistFromWishlistView");
        byte[] pic = wishList.getCover_pic();
        System.out.println(Arrays.toString(pic));
            try {
                wishList.setList_name(wishListName);
                wishList.setOccasion(occasion);
                if(!coverPic.isEmpty()) {
                    wishList.setCover_pic(coverPic.getBytes());
                }
                wishListRepository.updateWishList(wishList);
            } catch (IOException e) {
                e.printStackTrace();
            }

        return "redirect:/wishlist-page/"+wishList.getId();
    }

    //POST MAPPING FOR CREATING WISHLIST
    @PostMapping("/create-wishlist")
    public String createWishlist(@RequestParam("list-name") String wishListName,
                                 @RequestParam("occasion") String occasion,
                                 @RequestParam("cover-pic") MultipartFile coverPic,
                                 HttpSession session){

        try {
            User user = (User) session.getAttribute("user");
            WishList wishList = new WishList(user.getId(), wishListName, occasion, coverPic.getBytes());
            wishListRepository.addWishList(wishList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/overview";
    }


    @GetMapping("/wishlist-page/{wishlistID}")
    public String wishlistPage(@PathVariable("wishlistID") int wishlistID,
                               HttpSession session){

        User user = (User) session.getAttribute("user");
        WishList wishList = wishListRepository.getWishlistByIDAndUserID(wishlistID, user.getId());
        List<Wish> listOfWishes = wishRepository.getWishesByWishlistID(wishList.getId());
        session.setAttribute("wishlistFromWishlistView", wishList);
        session.setAttribute("listOfWishes", listOfWishes);

        return "wishlist-page";
    }

    @GetMapping("/wish-page/{wishID}")
    public String wishPage(@PathVariable("wishID") int wishID,
                           HttpSession session){

        Wish wish = wishRepository.getWishByID(wishID);
        session.setAttribute("wish", wish);
        return "wish-page";
    }


    @PostMapping("/create-wish")
    public String createWish(HttpSession session,
                             @RequestParam("wish-name") String wishName,
                             @RequestParam("description") String description,
                             @RequestParam("price") String price,
                             @RequestParam("quantity") String quantity,
                             @RequestParam("wish-pic") MultipartFile wishPic,
                             @RequestParam("url") String url){

        WishList wishList = (WishList) session.getAttribute("wishlistFromWishlistView");

        try{
            Wish wish = new Wish(wishList.getId(), wishName, description, price, quantity, wishPic.getBytes(), url);
            wishRepository.addWish(wish);
        }catch (IOException e){
            e.printStackTrace();
        }

        return "redirect:/wishlist-page/"+wishList.getId();
    }

    @PostMapping("update-wish/{wishlistID}/{wishID}")
    public String updateWish(HttpSession session,
                             @PathVariable("wishID") int id,
                             @RequestParam("wish-name") String wishName,
                             @RequestParam("description") String description,
                             @RequestParam("price") String price,
                             @RequestParam("quantity") String quantity,
                             @RequestParam("wish-pic") MultipartFile wishPic,
                             @RequestParam("url") String url){
        WishList wishList = (WishList) session.getAttribute("wishlistFromWishlistView");
        try{
            Wish wish = new Wish();
            wish.setId(id);
            wish.setWish_name(wishName);
            wish.setDescription(description);
            wish.setPrice(price);
            wish.setQuantity(quantity);
            if(!wishPic.isEmpty()) {
                wish.setWish_pic(wishPic.getBytes());
            }
            wish.setUrl(url);
            wishRepository.updateWish(wish);
        }catch (IOException e){
            e.printStackTrace();
        }
        return "redirect:/wishlist-page/"+wishList.getId();
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/delete-wishlist/{wishlistID}")
    public String deleteWishbook(@PathVariable("wishlistID") int id){
        wishListRepository.deleteWishListByID(id);

        return "redirect:/overview";
    }

    @GetMapping("/delete-wish/{wishID}")
    public String deleterWish(@PathVariable("wishID") int id,
                              HttpSession session){
        WishList wishList = (WishList) session.getAttribute("wishlistFromWishlistView");
        wishRepository.deleteWishByID(id);
        return "redirect:/wishlist-page/"+wishList.getId();
    }
}