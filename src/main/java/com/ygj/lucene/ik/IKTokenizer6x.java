package com.ygj.lucene.ik;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;

public class IKTokenizer6x extends Tokenizer{

    private IKSegmenter _IKImplement;

    private final CharTermAttribute termAttribute;

    private final OffsetAttribute offsetAttribute;

    private final TypeAttribute typeAttribute;

    private int endPosition;

    public IKTokenizer6x(boolean useSmart) {
        super();
        offsetAttribute = addAttribute(OffsetAttribute.class);
        termAttribute = addAttribute(CharTermAttribute.class);
        typeAttribute = addAttribute(TypeAttribute.class);
        _IKImplement = new IKSegmenter(input, useSmart);
    }

    @Override
    public boolean incrementToken() throws IOException {
        clearAttributes();
        Lexeme nextLexeme = _IKImplement.next();
        if(nextLexeme != null){
            termAttribute.append(nextLexeme.getLexemeText());
            termAttribute.setLength(nextLexeme.getLength());
            offsetAttribute.setOffset(nextLexeme.getBeginPosition(), nextLexeme.getEndPosition());

            endPosition = nextLexeme.getEndPosition();
            typeAttribute.setType(nextLexeme.getLexemeText());
            return true;
        }
        return false;
    }

    public void reset() throws IOException {
        super.reset();
        _IKImplement.reset(input);
    }

    public final void end(){
        int finalOffset = correctOffset(this.endPosition);
        offsetAttribute.setOffset(finalOffset, finalOffset);
    }
}
