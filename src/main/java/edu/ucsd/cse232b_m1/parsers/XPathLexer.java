// Generated from XPathGrammar.g4 by ANTLR 4.7.2

package edu.ucsd.cse232b_m1.parsers;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XPathLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, EQ=19, IS=20, ID=21, FILENAME=22, WHITESPACE=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "EQ", "IS", "ID", "FILENAME", "WHITESPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'//'", "'/'", "'doc(\"'", "'\")'", "'document(\"'", "'*'", "'.'", 
			"'..'", "'text()'", "'@'", "','", "'('", "')'", "'['", "']'", "'and'", 
			"'or'", "'not'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "EQ", "IS", "ID", "FILENAME", 
			"WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public XPathLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "XPathGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\31\u008b\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\5"+
		"\24s\n\24\3\25\3\25\3\25\3\25\5\25y\n\25\3\26\6\26|\n\26\r\26\16\26}\3"+
		"\27\6\27\u0081\n\27\r\27\16\27\u0082\3\30\6\30\u0086\n\30\r\30\16\30\u0087"+
		"\3\30\3\30\2\2\31\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\3\2\5\7\2//\62"+
		";C\\aac|\7\2\60\60\62;C\\aac|\5\2\13\f\17\17\"\"\2\u008f\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\3\61\3\2\2\2"+
		"\5\64\3\2\2\2\7\66\3\2\2\2\t<\3\2\2\2\13?\3\2\2\2\rJ\3\2\2\2\17L\3\2\2"+
		"\2\21N\3\2\2\2\23Q\3\2\2\2\25X\3\2\2\2\27Z\3\2\2\2\31\\\3\2\2\2\33^\3"+
		"\2\2\2\35`\3\2\2\2\37b\3\2\2\2!d\3\2\2\2#h\3\2\2\2%k\3\2\2\2\'r\3\2\2"+
		"\2)x\3\2\2\2+{\3\2\2\2-\u0080\3\2\2\2/\u0085\3\2\2\2\61\62\7\61\2\2\62"+
		"\63\7\61\2\2\63\4\3\2\2\2\64\65\7\61\2\2\65\6\3\2\2\2\66\67\7f\2\2\67"+
		"8\7q\2\289\7e\2\29:\7*\2\2:;\7$\2\2;\b\3\2\2\2<=\7$\2\2=>\7+\2\2>\n\3"+
		"\2\2\2?@\7f\2\2@A\7q\2\2AB\7e\2\2BC\7w\2\2CD\7o\2\2DE\7g\2\2EF\7p\2\2"+
		"FG\7v\2\2GH\7*\2\2HI\7$\2\2I\f\3\2\2\2JK\7,\2\2K\16\3\2\2\2LM\7\60\2\2"+
		"M\20\3\2\2\2NO\7\60\2\2OP\7\60\2\2P\22\3\2\2\2QR\7v\2\2RS\7g\2\2ST\7z"+
		"\2\2TU\7v\2\2UV\7*\2\2VW\7+\2\2W\24\3\2\2\2XY\7B\2\2Y\26\3\2\2\2Z[\7."+
		"\2\2[\30\3\2\2\2\\]\7*\2\2]\32\3\2\2\2^_\7+\2\2_\34\3\2\2\2`a\7]\2\2a"+
		"\36\3\2\2\2bc\7_\2\2c \3\2\2\2de\7c\2\2ef\7p\2\2fg\7f\2\2g\"\3\2\2\2h"+
		"i\7q\2\2ij\7t\2\2j$\3\2\2\2kl\7p\2\2lm\7q\2\2mn\7v\2\2n&\3\2\2\2os\7?"+
		"\2\2pq\7g\2\2qs\7s\2\2ro\3\2\2\2rp\3\2\2\2s(\3\2\2\2tu\7?\2\2uy\7?\2\2"+
		"vw\7k\2\2wy\7u\2\2xt\3\2\2\2xv\3\2\2\2y*\3\2\2\2z|\t\2\2\2{z\3\2\2\2|"+
		"}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~,\3\2\2\2\177\u0081\t\3\2\2\u0080\177\3"+
		"\2\2\2\u0081\u0082\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083"+
		".\3\2\2\2\u0084\u0086\t\4\2\2\u0085\u0084\3\2\2\2\u0086\u0087\3\2\2\2"+
		"\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a"+
		"\b\30\2\2\u008a\60\3\2\2\2\b\2rx}\u0082\u0087\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}