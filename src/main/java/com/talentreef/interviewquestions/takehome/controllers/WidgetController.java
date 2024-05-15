package com.talentreef.interviewquestions.takehome.controllers;

import com.talentreef.interviewquestions.takehome.models.Widget;
import com.talentreef.interviewquestions.takehome.services.WidgetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j
@RestController
@RequestMapping(value = "/v1/widgets", produces = MediaType.APPLICATION_JSON_VALUE)
public class WidgetController {

  private final WidgetService widgetService;

  public WidgetController(WidgetService widgetService) {
    Assert.notNull(widgetService, "widgetService must not be null");
    this.widgetService = widgetService;
  }

  @GetMapping
  public ResponseEntity<List<Widget>> getAllWidgets() {
    return ResponseEntity.ok(widgetService.getAllWidgets());
  }

  @GetMapping("/{name}")
  public ResponseEntity<Widget> getWidgetById(@PathVariable(value = "name") final String name) {
    Optional<Widget> widget = widgetService.getWidgetById(name);
    if (widget.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(widget.get());
  }

  @PostMapping()
  public ResponseEntity<Widget> saveWidget(@RequestBody Widget newWidget) {
    // we should validate fields restrictions as length before saving
    return ResponseEntity.ok(widgetService.saveWidget(newWidget));
  }

  @PutMapping("/{name}")
  public ResponseEntity<Widget> updateWidget(@PathVariable String name, @RequestBody Widget widget) {
    // we should validate fields restrictions as length before updating
    return ResponseEntity.ok(widgetService.updateWidget(name, widget));
  }

  @DeleteMapping("/{name}")
  public ResponseEntity<List<Widget>> deleteWidget(@PathVariable String name) {
    return ResponseEntity.ok(widgetService.deleteWidgetById(name));
  }
}
