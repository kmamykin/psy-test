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
import com.mamykin.psytest.client.model.StudySlide;
import com.mamykin.psytest.client.model.StudySlideElement;
import com.mamykin.psytest.client.model.elements.SlideChoiceAnswer;
import com.mamykin.psytest.client.model.elements.SlideImageElement;
import com.mamykin.psytest.client.model.elements.SlideParagraphElement;
import com.mamykin.psytest.client.model.elements.SlideSingleChoiceElement;
import com.mamykin.psytest.client.model.elements.SlideTextAreaElement;

public class StudyFactory {

	public Study createFromXml(String url) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {

			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parse using builder to get DOM representation of the XML file
			Document dom = db.parse(url);
			dom.getDocumentElement().normalize();
			return parseXmlDocument(dom);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return null;

	}

	private Study parseXmlDocument(Document dom) {
		Study study = new Study();
		study.setName(dom.getDocumentElement().getAttribute("name"));
		// read groups
		NodeList groups = dom.getElementsByTagName("group");
		for (int i = 0; i < groups.getLength(); i++) {
			Element node = (Element) groups.item(i);
			study.addGroup(node.getAttribute("name"));
		}
		// read cases
		NodeList cases = dom.getElementsByTagName("case");
		for (int i = 0; i < cases.getLength(); i++) {
			Element node = (Element) cases.item(i);
			study.addCase(node.getAttribute("name"));
		}
		// read slides
		NodeList slides = dom.getElementsByTagName("slide");
		for (int i = 0; i < slides.getLength(); i++) {
			Element node = (Element) slides.item(i);
			study.addSlide(parseSlide(node));
		}

		return study;
	}

	private enum ElementType {
		paragraph, image, singlechoice, textarea, imagesinglechoice
	}

	private StudySlide parseSlide(Element node) {
		StudySlide slide = new StudySlide();
		slide.setName(node.getAttribute("name"));
		NodeList elements = node.getChildNodes();
		for (int i = 0; i < elements.getLength(); i++) {
			if (elements.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element slideElement = (Element) elements.item(i);
				ElementType type = ElementType.valueOf(slideElement
						.getTagName());
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

				default:
					break;
				}
			}
		}
		return slide;
	}

	private StudySlideElement parseTextArea(Element slideElement) {
		return new SlideTextAreaElement(slideElement.getAttribute("id"));
	}

	private StudySlideElement parseSingleChoice(Element slideElement) {
		String id = slideElement.getAttribute("id");
		String question = slideElement.getElementsByTagName("question").item(0)
				.getTextContent();
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
				answer.setCorrect(answerElement.getAttribute("correct")
						.equalsIgnoreCase("true"));
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
		SlideImageElement image = new SlideImageElement(slideElement
				.getAttribute("id"), slideElement.getAttribute("url"));
		return image;
	}

	private StudySlideElement parseParagraph(Element slideElement) {
		return new SlideParagraphElement(slideElement.getTextContent());
	}

}
