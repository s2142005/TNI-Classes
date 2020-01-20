package com.arms.app.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.arms.domain.entity.Hello;
import com.arms.domain.repository.HelloRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/")
public class HelloController {

   @Autowired
    HelloRepository helloRepository;


   @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.addObject("list", helloRepository.findAll());
        modelAndView.setViewName("hello/list");
        return modelAndView;
    }
   @RequestMapping(value = "create", params = "form", method = RequestMethod.GET)
   public String create() {
       return "hello/create";
   }
   @RequestMapping(value = "create", method = RequestMethod.POST)
   public String doneCreate(@RequestParam("name") String name) {
       Hello hello = new Hello();
       hello.setName(name);
       helloRepository.save(hello);
       return "redirect:/list";
   }
   @RequestMapping(value = "list", method = RequestMethod.GET)
   public ModelAndView list(ModelAndView modelAndView) {
       modelAndView.addObject("list", helloRepository.findAll());
       modelAndView.setViewName("hello/list");
       return modelAndView;
   }
   @RequestMapping(value = "update/{id}", params = "form", method = RequestMethod.GET)
   public ModelAndView update(@PathVariable("id") int id, ModelAndView modelAndView) {
       modelAndView.addObject("hello", helloRepository.findOne(id));
       modelAndView.setViewName("hello/update");
       return modelAndView;
   }
   @RequestMapping(value = "update", method = RequestMethod.POST)
   public String update(@RequestParam("id") int id, @RequestParam String name) {
       Hello hello = helloRepository.findOne(id);
       hello.setName(name);
       helloRepository.save(hello);
       return "redirect:/list";
   }
   @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
   public String delete(@PathVariable("id") int id) {
       helloRepository.delete(id);
       return "redirect:/list";
   }


   

}