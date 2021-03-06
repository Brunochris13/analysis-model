package edu.hm.hafner.analysis.parser;

import org.junit.jupiter.api.Test;

import edu.hm.hafner.analysis.AbstractParserTest;
import edu.hm.hafner.analysis.FileReaderFactory;
import edu.hm.hafner.analysis.ParsingException;
import edu.hm.hafner.analysis.Report;
import edu.hm.hafner.analysis.Severity;
import edu.hm.hafner.analysis.assertions.SoftAssertions;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests the class {@link QtTranslationParser}.
 */
class QtTranslationParserTest extends AbstractParserTest {
    QtTranslationParserTest() {
        super("qttranslation/shouldParseAllIssues.ts");
    }

    @Override
    protected QtTranslationParser createParser() {
        return new QtTranslationParser();
    }

    @Override
    protected void assertThatIssuesArePresent(final Report report, final SoftAssertions softly) {
        String fileName = new FileReaderFactory(getResourceAsFile(getFileWithIssuesName())).getFileName();

        softly.assertThat(report).hasSize(4);

        softly.assertThat(report.get(0))
                .hasSeverity(Severity.WARNING_LOW)
                .hasLineStart(17)
                .hasLineEnd(20)
                .hasColumnStart(5)
                .hasColumnEnd(15)
                .hasMessage(QtTranslationParser.TRANSLATION_TYPE_UNFINISHED_MESSAGE)
                .hasFileName(fileName)
                .hasCategory(QtTranslationParser.TRANSLATION_TYPE_UNFINISHED);

        softly.assertThat(report.get(1))
                .hasSeverity(Severity.WARNING_NORMAL)
                .hasLineStart(21)
                .hasLineEnd(24)
                .hasColumnStart(5)
                .hasColumnEnd(15)
                .hasMessage(QtTranslationParser.TRANSLATION_TYPE_VANISHED_MESSAGE)
                .hasFileName(fileName)
                .hasCategory(QtTranslationParser.TRANSLATION_TYPE_VANISHED);

        softly.assertThat(report.get(2))
                .hasSeverity(Severity.WARNING_NORMAL)
                .hasLineStart(25)
                .hasLineEnd(28)
                .hasColumnStart(5)
                .hasColumnEnd(15)
                .hasMessage(QtTranslationParser.TRANSLATION_TYPE_OBSOLETE_MESSAGE)
                .hasFileName(fileName)
                .hasCategory(QtTranslationParser.TRANSLATION_TYPE_OBSOLETE);

        softly.assertThat(report.get(3))
                .hasSeverity(Severity.WARNING_LOW)
                .hasLineStart(46)
                .hasLineEnd(53)
                .hasColumnStart(5)
                .hasColumnEnd(15)
                .hasMessage(QtTranslationParser.TRANSLATION_TYPE_UNFINISHED_MESSAGE)
                .hasFileName(fileName)
                .hasCategory(QtTranslationParser.TRANSLATION_TYPE_UNFINISHED);
    }

    @Test
    protected void shouldParseAllIssuesSingleLine() {
        String relativeFileName = "qttranslation/shouldParseAllIssuesSingleLine.ts";
        Report report = parse(relativeFileName);
        String fileName = new FileReaderFactory(getResourceAsFile(relativeFileName)).getFileName();

        try (SoftAssertions softly = new SoftAssertions()) {
            softly.assertThat(report).hasSize(4);

            softly.assertThat(report.get(0))
                    .hasSeverity(Severity.WARNING_LOW)
                    .hasLineStart(1)
                    .hasLineEnd(1)
                    .hasColumnStart(338)
                    .hasColumnEnd(423)
                    .hasMessage(QtTranslationParser.TRANSLATION_TYPE_UNFINISHED_MESSAGE)
                    .hasFileName(fileName)
                    .hasCategory(QtTranslationParser.TRANSLATION_TYPE_UNFINISHED);

            softly.assertThat(report.get(1))
                    .hasSeverity(Severity.WARNING_NORMAL)
                    .hasLineStart(1)
                    .hasLineEnd(1)
                    .hasColumnStart(423)
                    .hasColumnEnd(513)
                    .hasMessage(QtTranslationParser.TRANSLATION_TYPE_VANISHED_MESSAGE)
                    .hasFileName(fileName)
                    .hasCategory(QtTranslationParser.TRANSLATION_TYPE_VANISHED);

            softly.assertThat(report.get(2))
                    .hasSeverity(Severity.WARNING_NORMAL)
                    .hasLineStart(1)
                    .hasLineEnd(1)
                    .hasColumnStart(513)
                    .hasColumnEnd(611)
                    .hasMessage(QtTranslationParser.TRANSLATION_TYPE_OBSOLETE_MESSAGE)
                    .hasFileName(fileName)
                    .hasCategory(QtTranslationParser.TRANSLATION_TYPE_OBSOLETE);

            softly.assertThat(report.get(3))
                    .hasSeverity(Severity.WARNING_LOW)
                    .hasLineStart(1)
                    .hasLineEnd(1)
                    .hasColumnStart(996)
                    .hasColumnEnd(1183)
                    .hasMessage(QtTranslationParser.TRANSLATION_TYPE_UNFINISHED_MESSAGE)
                    .hasFileName(fileName)
                    .hasCategory(QtTranslationParser.TRANSLATION_TYPE_UNFINISHED);
        }
    }

    @Test
    void duplicatedName() {
        assertThatThrownBy(() -> parse("qttranslation/duplicatedName.ts"))
                .isInstanceOf(ParsingException.class);
    }

    @Test
    void duplicatedSource() {
        assertThatThrownBy(() -> parse("qttranslation/duplicatedSource.ts"))
                .isInstanceOf(ParsingException.class);
    }

    @Test
    void duplicatedTranslation() {
        assertThatThrownBy(() -> parse("qttranslation/duplicatedTranslation.ts"))
                .isInstanceOf(ParsingException.class);
    }

    @Test
    void missingElementStart() {
        assertThatThrownBy(() -> parse("qttranslation/missingElementStart.ts"))
                .isInstanceOf(ParsingException.class);
    }

    @Test
    void missingName() {
        assertThatThrownBy(() -> parse("qttranslation/missingName.ts"))
                .isInstanceOf(ParsingException.class);
    }

    @Test
    void missingSource() {
        assertThatThrownBy(() -> parse("qttranslation/missingSource.ts"))
                .isInstanceOf(ParsingException.class);
    }

    @Test
    void missingTranslation() {
        assertThatThrownBy(() -> parse("qttranslation/missingTranslation.ts"))
                .isInstanceOf(ParsingException.class);
    }

    @Test
    protected void multilineTranslation() {
        String relativeFileName = "qttranslation/multilineTranslation.ts";
        Report report = parse(relativeFileName);
        String fileName = new FileReaderFactory(getResourceAsFile(relativeFileName)).getFileName();

        try (SoftAssertions softly = new SoftAssertions()) {
            softly.assertThat(report).hasSize(1);

            softly.assertThat(report.get(0))
                    .hasSeverity(Severity.WARNING_NORMAL)
                    .hasLineStart(6)
                    .hasLineEnd(11)
                    .hasColumnStart(5)
                    .hasColumnEnd(15)
                    .hasMessage(QtTranslationParser.TRANSLATION_TYPE_OBSOLETE_MESSAGE)
                    .hasFileName(fileName)
                    .hasCategory(QtTranslationParser.TRANSLATION_TYPE_OBSOLETE);
        }
    }

    @Test
    void rootElementHasParent() {
        assertThatThrownBy(() -> parse("qttranslation/rootElementHasParent.ts"))
                .isInstanceOf(ParsingException.class);
    }

    @Test
    void unknownTranslationType() {
        assertThatThrownBy(() -> parse("qttranslation/unknownTranslationType.ts"))
                .isInstanceOf(ParsingException.class);
    }

    @Test
    void wrongParent() {
        assertThatThrownBy(() -> parse("qttranslation/wrongParent.ts"))
                .isInstanceOf(ParsingException.class);
    }
}