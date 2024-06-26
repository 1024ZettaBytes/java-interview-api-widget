package com.talentreef.interviewquestions.takehome.services;

import com.talentreef.interviewquestions.takehome.models.Widget;
import com.talentreef.interviewquestions.takehome.respositories.WidgetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class WidgetService {

  private final WidgetRepository widgetRepository;

  @Autowired
  private WidgetService(WidgetRepository widgetRepository) {
    Assert.notNull(widgetRepository, "widgetRepository must not be null");
    this.widgetRepository = widgetRepository;
  }

  public List<Widget> getAllWidgets() {
    return widgetRepository.findAll();
  }

  public Optional<Widget> getWidgetById(String name) {
    return widgetRepository.findById(name);
  }

  public Widget saveWidget(Widget widget) {
    return widgetRepository.save(widget);
  }

  public Widget updateWidget(String name, Widget widget) {
    widget.setName(name);
    return widgetRepository.save(widget);
  }

  public List<Widget> deleteWidgetById(String name) {
    return widgetRepository.deleteById(name);
  }

}
