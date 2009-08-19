package com.mamykin.psytest.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mamykin.psytest.client.model.Study;
import com.mamykin.psytest.client.model.StudyBlock;
import com.mamykin.psytest.client.model.StudyCase;
import com.mamykin.psytest.client.model.StudyGroup;
import com.mamykin.psytest.client.model.StudySlide;
import com.mamykin.psytest.client.model.StudySlideElement;
import com.mamykin.psytest.client.model.elements.SlideChoiceAnswer;
import com.mamykin.psytest.client.model.elements.SlideImageElement;
import com.mamykin.psytest.client.model.elements.SlideMultiChoiceElement;
import com.mamykin.psytest.client.model.elements.SlideParagraphElement;
import com.mamykin.psytest.client.model.elements.SlideSingleChoiceElement;
import com.mamykin.psytest.client.model.elements.SlideTextAreaElement;
import com.mamykin.psytest.client.model.elements.StudyPlaceholderElement;

public class StudyFactory {

	public Study createFromXml(String url) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {

			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parse using builder to get DOM representation of the XML file
			Document dom = db.parse(url);
			dom.getDocumentElement().normalize();
			return parseStudy(dom);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return null;

	}

	private Study parseStudy(Document dom) {
		Study study = new Study();
		study.setName(dom.getDocumentElement().getAttribute("name"));
		study.setCaseCount(Integer.parseInt(dom.getDocumentElement().getAttribute("casecount")));

		// read slide templates
		for (Element node : selectGroupedChildrenElements(dom.getDocumentElement(), "slidetemplates", "slidetemplate")) {
			study.addSlideTemplate(parseSlideTemplate(node));
		}

		// read blocks with cases and slides
		for (Element node : selectGroupedChildrenElements(dom.getDocumentElement(), "blocks", "block")) {
			study.addBlock(parseBlock(node));
		}

		// read groups
		for (Element node : selectGroupedChildrenElements(dom.getDocumentElement(), "groups", "group")) {
			study.addGroup(parseGroup(node));
		}

		return study;
	}

	private StudyGroup parseGroup(Element element) {
		List<String> blockRefs = new ArrayList<String>();
		for (Element node : selectChildElements(element, "block")) {
			blockRefs.add(node.getAttribute("ref"));
		}
		return new StudyGroup(element.getAttribute("name"), blockRefs);
	}

	private StudyBlock parseBlock(Element node) {
		return new StudyBlock(node.getAttribute("id"), parseCases(node));
	}

	private List<StudyCase> parseCases(Element element) {
		List<StudyCase> cases = new ArrayList<StudyCase>();
		for (Element node : selectChildElements(element, "case")) {
			cases.add(parseCase(node));
		}
		return cases;
	}

	private StudyCase parseCase(Element node) {
		return new StudyCase(node.getAttribute("id"), parseSlides(node));
	}

	private List<StudySlide> parseSlides(Element element) {
		List<StudySlide> slides = new ArrayList<StudySlide>();
		for (Element node : selectChildElements(element, "slide")) {
			slides.add(parseSlide(node));
		}
		return slides;
	}

	private enum ElementType {
		paragraph, image, singlechoice, textarea, multichoice, placeholder
	}

	private StudySlide parseSlide(Element node) {
		StudySlide slide = new StudySlide();
		slide.setTemplate(node.getAttribute("template"));
		parseSlideElements(node, slide);
		return slide;
	}

	private StudySlide parseSlideTemplate(Element node) {
		StudySlide slide = new StudySlide();
		slide.setName(node.getAttribute("name"));
		if (node.hasAttribute("timelimit")) {
			slide.setTimeLimitInSec(Integer.parseInt(node.getAttribute("timelimit")));
		}
		parseSlideElements(node, slide);
		return slide;
	}

	private void parseSlideElements(Element node, StudySlide slide) {
		NodeList elements = node.getChildNodes();
		for (int i = 0; i < elements.getLength(); i++) {
			if (elements.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element slideElement = (Element) elements.item(i);
				ElementType type = ElementType.valueOf(slideElement.getTagName());
				switch (type) {
				case paragraph:
					slide.addElement(parseParagraph(slideElement));
					break;
				case image:
					slide.addElement(parseImage(slideElement));
					break;
				case singlechoice:
					slide.addElement(parseSingleChoice(slideElement));
					break;
				case textarea:
					slide.addElement(parseTextArea(slideElement));
					break;
				case multichoice:
					slide.addElement(parseMultiChoice(slideElement));
					break;
				case placeholder:
					slide.addElement(parsePlaceholder(slideElement));
					break;

				default:
					break;
				}
			}
		}
	}

	private StudySlideElement parsePlaceholder(Element slideElement) {
		return new StudyPlaceholderElement();
	}

	private StudySlideElement parseMultiChoice(Element slideElement) {
		String id = slideElement.getAttribute("id");
		String question = slideElement.getElementsByTagName("question").item(0).getTextContent();
		List<String> choices = new ArrayList<String>();
		NodeList choiceNodes = slideElement.getElementsByTagName("choice");
		for (int i = 0; i < choiceNodes.getLength(); i++) {
			Element choiceElement = (Element) choiceNodes.item(i);
			choices.add(choiceElement.getTextContent());
		}
		return new SlideMultiChoiceElement(id, question, choices);
	}

	private StudySlideElement parseTextArea(Element slideElement) {
		return new SlideTextAreaElement(slideElement.getAttribute("id"), slideElement.getAttribute("optional"));
	}

	private StudySlideElement parseSingleChoice(Element slideElement) {
		String id = slideElement.getAttribute("id");
		String question = slideElement.getElementsByTagName("question").item(0).getTextContent();
		List<SlideChoiceAnswer> answers = parseChoiceAnswers(slideElement);
		return new SlideSingleChoiceElement(id, question, answers);
	}

	private List<SlideChoiceAnswer> parseChoiceAnswers(Element slideElement) {
		ArrayList<SlideChoiceAnswer> answers = new ArrayList<SlideChoiceAnswer>();
		NodeList answerNodes = slideElement.getElementsByTagName("answer");
		for (int i = 0; i < answerNodes.getLength(); i++) {
			Element answerElement = (Element) answerNodes.item(i);
			SlideChoiceAnswer answer = new SlideChoiceAnswer();

			if (answerElement.hasAttribute("correct")) {
				answer.setCorrect(answerElement.getAttribute("correct").equalsIgnoreCase("true"));
			}
			if (answerElement.hasAttribute("url")) {
				answer.setImageUrl(answerElement.getAttribute("url"));
			}
			answer.setText(answerElement.getTextContent());
			answers.add(answer);
		}
		return answers;
	}

	private StudySlideElement parseImage(Element slideElement) {
		SlideImageElement image = new SlideImageElement(slideElement.getAttribute("id"), slideElement.getAttribute("url"));
		return image;
	}

	private StudySlideElement parseParagraph(Element slideElement) {
		return new SlideParagraphElement(slideElement.getTextContent(), slideElement.getAttribute("align"), slideElement.getAttribute("style"));
	}

	private List<Element> selectGroupedChildrenElements(Element element, String groupElementTag, String childElementTag) {
		Element parentElement = (Element) element.getElementsByTagName(groupElementTag).item(0);
		return selectChildElements(parentElement, childElementTag);
	}

	private List<Element> selectChildElements(Element element, String childElementTag) {
		List<Element> elements = new ArrayList<Element>();
		NodeList childElementNodes = element.getElementsByTagName(childElementTag);
		for (int i = 0; i < childElementNodes.getLength(); i++) {
			Element node = (Element) childElementNodes.item(i);
			elements.add(node);
		}
		return elements;
	}
}
