package com.targaryen.octopus.controller;

import com.targaryen.octopus.dto.MessageDto;
import com.targaryen.octopus.service.MessageService;
import com.targaryen.octopus.service.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/octopus", produces= MediaType.TEXT_HTML_VALUE)
public class NotificationController {
    private MessageService messageService;

    @Autowired
    public NotificationController(ServiceFactory serviceFactory) {
        this.messageService = serviceFactory.getMessageService();
    }

    @RequestMapping(value = "/hr/notification/list", method = RequestMethod.GET)
    public String hrNotificationList(ModelMap map) {
        List<MessageDto> messageDtoList = messageService.viewHrAll();

        map.addAttribute("notificationList", messageDtoList);

        int unreadCount = 0, starredCount = 0;
        for (MessageDto messageDto: messageDtoList) {
            if (!messageDto.isRead()) ++unreadCount;
            if (messageDto.isStarred()) ++starredCount;
        }

        // Summary
        map.addAttribute("unreadCount", unreadCount);
        map.addAttribute("starredCount", starredCount);
        map.addAttribute("allCount", messageDtoList.size());

        return "hr-notification-list";
    }

    @RequestMapping(value = "/hr/notification/mark/read", method = RequestMethod.POST)
    @ResponseBody
    public void hrNotificationMarkAsRead(@RequestParam(value="chkArray[]") int[] chkArray) {
        messageService.batchMarkAsRead(chkArray);
    }

    @RequestMapping(value = "/hr/notification/mark/unread", method = RequestMethod.POST)
    @ResponseBody
    public void hrNotificationMarkAsUnread(@RequestParam(value="chkArray[]") int[] chkArray) {
        messageService.batchMarkAsUnread(chkArray);
    }

    /* Single */
    @RequestMapping(value = "/hr/notification/mark/{messageId}/read", method = RequestMethod.POST)
    @ResponseBody
    public void hrNotificationMarkOneRead(@PathVariable("messageId") int messageId) {
        messageService.markOneAsRead(messageId);
    }

    @RequestMapping(value = "/hr/notification/mark/{messageId}/starred", method = RequestMethod.POST)
    @ResponseBody
    public void hrNotificationMarkOneStarred(@PathVariable("messageId") int messageId) {
        messageService.markOneAsStarred(messageId);
    }

    @RequestMapping(value = "/hr/notification/mark/{messageId}/unstarred", method = RequestMethod.POST)
    @ResponseBody
    public void hrNotificationMarkOneUnstarred(@PathVariable("messageId") int messageId) {
        messageService.markOneAsUnstarred(messageId);
    }
}
