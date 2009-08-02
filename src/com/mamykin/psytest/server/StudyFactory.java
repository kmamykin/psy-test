package com.mamykin.psytest.server;

import java.io.IOException;

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
import com.mamykin.psytest.client.model.elements.StudySlideImage;
import com.mamykin.psytest.client.model.elements.StudySlideParagraph;
import com.mamykin.psytest.client.model.elements.StudySlideSingleChoice;
import com.mamykin.psytest.client.model.elements.StudySlideTextArea;

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
		paragraph, image, singlechoice, textarea
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
		return new StudySlideTextArea(slideElement.getAttribute("id"));
	}

	private StudySlideElement parseSingleChoice(Element slideElement) {
		StudySlideSingleChoice singleChoice = new StudySlideSingleChoice();
		singleChoice.setId(slideElement.getAttribute("id"));
		String question = slideElement.getElementsByTagName("question").item(0)
				.getTextContent();
		singleChoice.setQuestion(question);
		NodeList answers = slideElement.getElementsByTagName("answer");
		for (int i = 0; i < answers.getLength(); i++) {
			Element answer = (Element) answers.item(i);
			boolean correctAnswer = answer.hasAttribute("correct")
					&& answer.getAttribute("correct").equalsIgnoreCase("true");
			String answerText = answer.getTextContent();
			if (correctAnswer) {
				singleChoice.setCorrectAnswerIndex(i);
			}
			singleChoice.addAnswer(answerText);
		}
		return singleChoice;
	}

	private StudySlideElement parseImage(Element slideElement) {
		StudySlideImage image = new StudySlideImage(slideElement
				.getAttribute("id"), slideElement.getAttribute("url"));
		return image;
	}

	private StudySlideElement parseParagraph(Element slideElement) {
		return new StudySlideParagraph("", slideElement.getTextContent());
	}

}
