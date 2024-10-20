package com.boyang.assessment.question;

import com.boyang.assessment.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The abstract class which implements the {@link Question} interface.
 * It is used to create the specific question instance and store it.
 *
 * @author Boyang Wang
 */
public abstract class QuestionFactory implements Question {
    /**
     * Denotes the free response question type.
     */
    public static final String FREE_RESPONSE = "FREE_RESPONSE";

    /**
     * Denotes the multiple choices question type.
     */
    public static final String MULTIPLE_CHOICES = "MULTIPLE_CHOICES";

    /**
     * The map used to store every question instance created by the factory.
     */
    public static final Map<String, List<Question>> QUESTION_MAP = Map.of(
            FREE_RESPONSE, new ArrayList<>(),
            MULTIPLE_CHOICES, new ArrayList<>()
    );

    /**
     * The formulation of the question.
     */
    private final String formulation;

    /**
     * The constructor of {@link QuestionFactory}.
     * This method is used by the subclass's constructor for creating instance.
     * Recommend to use {@link QuestionFactory#getInstance(String, String)} or {@link QuestionFactory#getInstance(String, List)}
     * to get a question instance.
     *
     * @param formulation The formulation of the question.
     * @throws IllegalArgumentException if the formulation is null or empty.
     */
    protected QuestionFactory(String formulation) {
        if (StringUtil.isEmpty(formulation)) {
            throw new IllegalArgumentException("The formulation cannot be null or empty!");
        }
        this.formulation = formulation;
    }

    /**
     * @see Question#getFormulation()
     */
    @Override
    public String getFormulation() {
        return formulation;
    }

    /**
     * Get a {@link FreeResponseQuestion} instance and add this instance into {@link QuestionFactory#QUESTION_MAP}.
     *
     * @param formulation The formulation of the question.
     * @param answer      The correct answer of the question.
     * @return A {@link FreeResponseQuestion} instance.
     * @throws IllegalArgumentException Check {@link FreeResponseQuestion#FreeResponseQuestion(String, String)}.
     */
    public static Question getInstance(String formulation, String answer) {
        FreeResponseQuestion freeResponseQuestion = new FreeResponseQuestion(formulation, answer);
        QUESTION_MAP.get(FREE_RESPONSE).add(freeResponseQuestion);
        return freeResponseQuestion;
    }

    /**
     * Get a {@link MultipleChoicesQuestion} instance and add this instance into {@link QuestionFactory#QUESTION_MAP}.
     *
     * @param formulation The formulation of the question.
     * @param answer      The correct answer of the question.
     * @return A {@link MultipleChoicesQuestion} instance.
     * @throws IllegalArgumentException Check {@link MultipleChoicesQuestion#MultipleChoicesQuestion(String, List)}.
     */
    public static Question getInstance(String formulation, List<String> answer) {
        MultipleChoicesQuestion multipleChoicesQuestion = new MultipleChoicesQuestion(formulation, answer);
        QUESTION_MAP.get(MULTIPLE_CHOICES).add(multipleChoicesQuestion);
        return multipleChoicesQuestion;
    }

    /**
     * Override the {@link Object#equals(Object)} method.
     * If 2 questions have the same formulation, they are the same question.
     *
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionFactory questionFactory = (QuestionFactory) o;
        return formulation.equals(questionFactory.formulation);
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(formulation);
    }
}
